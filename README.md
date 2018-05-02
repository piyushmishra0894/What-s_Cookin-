# What-s_Cookin-

Connecting Firebase to android application:

1. Click Tools > SDK Manager.
2. Click the SDK Tools tab.
3. Check the Google Repository checkbox, and click OK.
4. Click OK to install.
5. Click Background to complete the installation in the background, or wait for the installation to complete and click Finish.

You can follow the steps here for more guided information: https://developer.android.com/studio/write/firebase

Using Cloud Vision API in android application:

Here is a very useful guided link to use CLoud Vision API in an android application : https://code.tutsplus.com/tutorials/how-to-use-the-google-cloud-vision-api-in-android-apps--cms-29009
Deployment:

Android application deployment steps:

1. Open the project in Android Studio.
2. Go to Build->Clean Project.
3. Go to Build->Rebuild Project

The above steps ensure that there are no errors in the imported project.

4. Go to Build->Generate Signed APK…
5. Create a new keystore or give the path to an existing keystore.
Note: If creating a new keystore, remember the password provided.
6. Fill in the rest of the details and click on Next.
7. Choose appropriate APK Destination folder.
8. Select Build type as Release.
9. Choose V2 (Full APK Signature).
10. Click on Finish.

The APK will now be available for distribution at your chosen destination folder.
While installing the APK on your Android phone, the following steps will need to be performed:

1. Navigate to Setting > Security.
2. Check the option "Unknown sources".
3. Tap OK on the prompt message.
4. Select "Trust".