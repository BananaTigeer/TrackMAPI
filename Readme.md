# __Overview__

This is my project proposal for MS3's basecamp training.
This API tracks movies, video-games, and music. Developed using Spring-boot following MVC pattern with MongoDB as database.


## Technologies Used:
* Java
* Maven
* Spring-Boot
* MongoDB
* Keycloak
* Postman
* JMeter
* NodeJs
* Newman CLI
* Newman-Repoter-Extra
* Jenkins
* Docker
* Docker-compose

## Setting it up:
This part shows how to setup the necessary tools and how to run the application.

Assuming that Java, Maven, MongoDB, NodeJs, Postman, JMeter, Docker, and Docker-Compose is installed through downloading executables in Windows from official sites or via Package Manager in a linux distribution. The following steps shows running and configuring things that require extra steps when setting it up:

* Installing Newman CLI with Node Package Manager
* Installing Newman-Repoter-Extra
* Downloading and running standalone Keycloak
* Configuring Keycloak
* Downloading and running standalone Jenkins
* Configuring Jenkins

### __Installing Newman CLI with Node Package Manager__
In terminal

>$ npm install -g newman

### __Installing newman-reporter-htmlextra__
In terminal

>$ npm install -g newman-reporter-extra

### __Downloading and running Keycloak__
Download [Keycloak Server](https://www.keycloak.org/downloads.html) then extract package when finished. Open terminal and navigate to the bin folder of the extracted package. For example:
> $ cd home/Desktop/keycloak-11.0.3/bin

then run the server:

> $ ./standalone.sh

By default, Keycloak uses port 8080 which coincidentally, used also by SpringBoot. To avoid conflicts, we will change what port Keycloak will run on. Navigate to `/keycloak-root/standalone/configuration/` and open `standalone.xml` and change http port to `9000`.

To start Keycloak, navigate to this address in your browser: 
> localhost:9000

### __Configuring Keycloak__
We will need to do some configurations in order for this project to work. For first time users, you will be prompted to make an admin user. 

Once an admin user account has been made, log in then create a new `realm` and name it `trackmapi`. Open `Clients` found on the menu, then once inside, hit `Create`.

Input the following values:
>Client ID: spring-boot-demo <br>
>Client Protocol: openid-connect <br>
>Access Type: confidential <br>
>Valid Redirect URIs: http://localhost:8080/* <br> <br>
>expand the Authentication Flow Overrides<br> <br>
>Browser Flow: direct grant <br>
>Direct Grant Flow: direct grant

Open Credentials tab to get the secret value. Then click Roles from the Dashboard Menu then hit `Add Role`. Input user for the Role Name then save.

Going back to the Dashboard Menu, open `Users` under Manage. Click `Add User` then input `demouser` and save. Go to credentials tab and add a password, with temporary setting off. Then open Role Mappings and assign `user` from Available Roles to Assigned Roles 



### __Downloading and running standalone Jenkins__
Download standalone Jenkins from [here](https://www.jenkins.io/download/) and select Generic Java Package (.war) from either LTS or non LTS version

Navigate to the directory where jenkins was downloaded and run command
>$ -java -jar jenkins.war --httpPort=9090

Picking a different port is fine, but only if that port is unused.

### __Configuring Jenkins__
Launch a browser and access Jenkins through this URL
> localhost:9090

Where 9090 is the port we specified when running jenkins

You will be asked for a username and password. For username, write `admin` and for password, navigate to your `/home/.jenkins/secrets/` directory and open `initialAdminPassword.txt` then use what's written inside as the password.

### Jenkins Plugin
We need to install *HTML Publisher* plugin for our html reports to work. Open `Manage Plugins` from Dasboard, then `Manage Plugins`, then `Available` tab, and search `HTML Publisher`.

### Creating a Pipeline
From the dashboard, select `New Item`, enter a project name, and select `Pipeline`, then click `Ok`. We should be seeing 4 tabs namely General, Build Triggers, Advanced Project Options, and Pipeline. Select `Advanced Project Options` and pick `Pipeline from Script` from the dropdown under `Definition`. 

Select `Git` from under `SCM` dropdown menu and add this repository
> https://github.com/BananaTigeer/TrackMAPI.git

For the `Branch Specifier` in `Branches to Build`, copy and paste:
> */feature/noDeployStage

Under `Script Path`:
>jenkinsfile 

Note: The Jenkins filename under Script Path must be the equal to the Jenkins filename from the repository's root directory. 

Once finished, hit `Save`.

### One last configuration
For some reason, the published HTML reports either won't display anything at all or be outright broken. (To do: confirm if CSS and security issue). To fix this, head back to the `Dashboard`, `Manage Jenkins`, `Script Console`, then copy and paste this line then run: 
> System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "") 

### Running the Pipeline
To run the pipeline, simply select `Build now` then wait for the pipeline to finish.

### Viewing Reports
Since we have the `HTML Plugin` addon installed, we should be able to view published HTML reports as seen from the same list where `Build now` is listed:
* Jacoco Report
* Postman Report
* JMeter Report

If the page shows a broken or empty formatting. Then running a specific command in the Script Console and running the pipeline again will fix this. See `One Last Configuration section` above.

## Schema
![test](https://i.ibb.co/WKn26hk/trackmapi-nosql-schema.png)