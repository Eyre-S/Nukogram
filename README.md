# Nukogram

> [Telegram](https://telegram.org) is a messaging app with a focus on speed and security. It’s superfast, simple and free.
> This repo contains the official source code for [Telegram App for Android](https://play.google.com/store/apps/details?id=org.telegram.messenger).

**Nukogram** is an free and open source fork from [Telegram App for Android](https://github.com/DrKLO/Telegram),
~~with some features from [Nekogram]() & [NekoX](https://github.com/NekoX-Dev/NekoX/) rebuilt~~,
~~with some features originally added~~.

## Changes with Official

- Launcher icons
  - newly *Nuko Vanilla* based on nekogram (as default)
  - transplant *nArrow* from nekogram-na fork.

#### technical changes

- [technical] removed huawei (agconnect) build.

## Reproducing Build Guide

*Note: We support building on Windows.*

1. Clone this repository to your workspace.
2. Make sure that you have Android SDK 8.1 and Android NDK rev. 20, and an IDE like Android Studio or IntellijIDEA(it's fine too).
3. Open this project in your IDE.
4. *(Optional)* [Obtain your own telegram `api_id` and `api_hash`](https://core.telegram.org/api/obtaining_api_id),
   write the value to `TELEGRAM_APP_ID` and `TELEGRAM_APP_HASH` in `./gradle.properties`.
   (if not, you will use Nukogram app_id).
5. *(Optional)* Set your own app package-name in field `APP_PACKAGE` in `./gradle.properties`.
   (if not, you will use nukogram package-name(cc.sukazyo.nukogram) as your app package-name,
   **it may cause conflict with Nukogram Official App.**)
6. *(Optional)* Go to [Google Firebase Console](https://console.firebase.google.com/),
   create two android apps with application IDs `${APP_PACKAGE}` and `${APP_PACKAGE}.beta`
   (the APP_PACKAGE is just the app package-name you set in [3.]),
   turn on firebase messaging and download `google-services.json`,
   overwrite `./TMessagesProj/google-services.json` with the downloaded.
   (if non set, or skiped [3.], you will use google service (like GCM/FCM) owned by Nukogram Official.)
7. *(Optional only debug build)* generate your own android-release.keystore, put it to `./TMessagesProj/config/release.keystore`,
   and set `SIGNING_KEYSTORE_PWD`(your keystore password), `SIGNING_KEY_ALIAS`(your keystore key alias), `SIGNING_KEY_PWD`(your keystore key password) in `./local.properties`.
   (if not, your apk will sign by a key generated randomly by android sdk, and you will recieve a warning "Release signing config not set";
   if you are building with release config, you will get an error that signing key unavailable)
8. Now you can build and run it, just as a normal Android App.
