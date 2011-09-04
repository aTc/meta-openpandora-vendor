DESCRIPTION = "Task file for default core/console apps and libs in the Pandora image"

# Use this task as a base to ship all kernel modules and make sure firmware and drivers are installed for BT and WiFi.
# Please see metadata/openpandora.oe.git/packages/pandora-system/pandora-firmware/pandora-firmware/readme.txt for info on the hacks for firmware.

# Don't forget to bump the PR if you change it.

PR = "r35"
LICENSE = "MIT"

inherit task 

AUFS = " \
  aufs2-util \
"

BLUETOOTH = " \
  blueprobe \
  bluez4 gst-plugin-bluez \
  libsndfile1 libasound-module-bluez \
"

# Package up the boot scripts and bootchart to help us work to drop the startup time.
BOOT = " \
  pandora-uboot-scripts \
  bootchart \
  mtd-utils \
"

# Package BOOST libs so people can use them in apps. It will pull in the RRECOMENDS.
BOOST = " \
  boost \
"

WIRELESS = " \
  pandora-firmware \
  wl1251-modules \
  wireless-tools \
  wpa-supplicant \  
  networkmanager netm-cli \
"

MEDIA_LIBS = " \
  libmodplug \
  libsdl-x11 libsdl-mixer libsdl-image \
  libsdl-gfx libsdl-net libsdl-ttf \
  libpng libpng12 libpng3 \
  faad2 \
  mikmod \
  speex \  
  flac \
  audiofile \
"
OPENGLES = " \
  omap3-sgx-modules devmem2 \
  libgles-omap3 \
"

PAM = " \
  libpam \
  libpam-meta \
"

PANDORA_LIBS = " \
  pandora-libpnd lsof \
  omap3-deviceid \
  pandora-skel \
  pandora-state \
"

SUDO = " \
  sudo sudo-enable-wheel-group \ 
  pandora-sudoers \
"

SSH = " \
  openssh-scp \
  openssh-ssh \
"

TOUCHSCREEN = " \
  tslib tslib-tests tslib-calibrate pointercal \
"

FS_SUPPORT = " \
  nfs-utils-client \
  fuse fuse-utils \
  sshfs-fuse gmailfs curlftpfs \
  ntfs-3g \
  squashfs-tools \
"

EXTRA_TOOLS = " \
  avahi \
  fbgrab fbset fbset-modes \
  portmap \
  file \
  socat \
  strace \
  screen \
  rsync \
  unrar \
  ksymoops \
  kexec-tools \
  zip \        
  gzip \
  bash \
  bzip2 \  
  minicom \
  nano \
  gdb \
  sessreg \
  lua5.1 \
  tzdata \
"

# Add extra util-linux-ng utils to image. 
# TODO: Fix util-linux-ng to meta depend on all subpackages.
UTIL_LINUX_NG_EXTRAS = " \
  util-linux-ng-losetup util-linux-ng-mountall \
  util-linux-ng-swaponoff \
"
  
RDEPENDS_${PN} = "\
  task-base-extended \
  task-proper-tools \
  ${AUFS} \
  ${BOOST} \
  ${WIRELESS} \
  ${BLUETOOTH} \  
  ${BOOT} \
  ${MEDIA_LIBS} \
  ${OPENGLES} \
  ${PANDORA_LIBS} \
  ${PAM} \  
  ${SSH} \
  ${SUDO} \
  ${TOUCHSCREEN} \
  ${FS_SUPPORT} \
  ${EXTRA_TOOLS} \
  ${UTIL_LINUX_NG_EXTRAS} \
  python-pygame \
  python-misc \
  python-modules \
  alsa-utils alsa-utils-alsactl alsa-utils-alsamixer alsa-utils-aplay \
  rdesktop \
  \
  angstrom-led-config \ 
"

# Make sure we install all kernel modules with the Pandora images
RRECOMMENDS_${PN} += "kernel-modules"

PACKAGE_ARCH = "${MACHINE_ARCH}"
