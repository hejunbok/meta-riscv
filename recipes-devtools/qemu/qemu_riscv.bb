require recipes-devtools/qemu/qemu.inc

SRC_URI = "gitsm://github.com/riscv/riscv-qemu.git;destsuffix=${S};branch=master;rebaseable=1 \
          "
SRCREV = "62b9b076d9d37117696ec64f0b3544c1205ff7f9"

SRC_URI_remove_class-native = "\
    file://fix-libcap-header-issue-on-some-distro.patch \
    file://cpus.c-qemu_cpu_kick_thread_debugging.patch \
    "

LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac"

QEMU_TARGETS = "riscv64 riscv32"

EXTRA_OECONF_remove = "--disable-numa --disable-lzo --disable-opengl --disable-gnutls --disable-static"

do_install_append() {
    # Prevent QA warnings about installed ${localstatedir}/run
    if [ -d ${D}${localstatedir}/run ]; then rmdir ${D}${localstatedir}/run; fi
}

COMPATIBLE_HOST_class-target = "(riscv64|riscv32).*-linux"
