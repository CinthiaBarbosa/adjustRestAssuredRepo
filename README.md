# adjustRestAssuredRepo


*** Instructions for installing project and running the tests ***

1) Github project (https://github.com/CinthiaBarbosa/adjustRestAssuredRepo)

2) Download Java

3) Download and install Eclipse IDE (Java IDE for java developers: https://www.eclipse.org/downloads/packages/release/kepler/sr1/eclipse-ide-java-developers)

4) For easier and quicker project installing, import the project .zip file which is on the downloaded repository path 'adjustRestAssuredRepo' > 'RestAssuredTask' > 'QA_Task_Project_restAssured.zip'. On Eclipse, go to 'File' > 'Import' > 'Existing Projects into Workspace' > Select 'Select archive file' option. At this point, add the path to mentioned project .zip file (QA_Task_Project_restAssured.zip), mark all checkboxes and select "Finish".

5) Now, project will be imported and it's ready to be executed!

6) Install TestNG plugin just in case: Help > Install new software : link: https://dl.bintray.com/testng-team/testng-eclipse-release > When TestNG plugin checkbox loads, select checkbox. In case plugin is already installed, "Finish" option won't be enabled. If plugin is not yet installed, "Finish" will be enabled. If enabled, please select "Finish".

7) All other dependencies should be already under the pom.xml file (e.g. for rest assured, etc).

8) For running the test, select the Java class under package 'src/test/java' > 'default' > 'Adjust_Task2_Booking_System_Create_Fetch_Update_Delete.java' > Right click on the class (or select "Run") > 'Run As' > 'TestNG Test'. At this point, tests should be run and you should see the report of passed / failed ones on the Console tab. Also, for more details on fail reason, check the TestNG results tab.

9) For detail and explanation why the last two test methods (Update and Delete) are failing can be seen on the video attached on the repo ('adjustRestAssuredRepo' > 'RestAssuredTask' > 'QA_Task_Deliverables_Cinthia_Pereira.zip'). Unzip it and watch the video!). Obs: This .zip file also contains the bug reports PDF for payment form for Task1.
