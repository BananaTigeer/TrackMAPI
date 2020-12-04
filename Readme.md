# __Overview__

This API tracks how many movies a user may have watched as well as the video-games a user may have played or the tv-series they watched.


This API has 4 resources namely:


* Movies
* TVSeries
* Games
* User

The 4 resources also have the following HTTP request methods: 


* POST
* GET 
* PUT
* PATCH
* DELETE


When building in Jenkins, this API runs locally and is dependent on Keycloak, Jenkins, and MongoDB all running locally. However,when finished building in Jenkins, we can then call up docker-compose to dockerize Spring-boot, MongoDb, and Keycloak. 

This Readme provides a guide on how to setup the project locally as well as build the pipeline in jenkins and running Docker-compose to run the built and pushed Spring-boot image and link it with MongoDb and Keycloak that are dockerized as well.

## __Technologies Used:__
* Java
* Maven
* Spring-Boot
* MongoDB
* Keycloak
* Postman
* JMeter
* NodeJs
* Newman CLI
* Newman-Reporter-Extra
* Jenkins
* Docker
* Docker-compose

## __Setting it up:__
This part shows how to setup the necessary tools and how to run the application.

Assuming that Java, Maven, MongoDB, NodeJs, Postman, JMeter, Docker, and Docker-Compose is installed through downloading executables in Windows from official sites or via Package Manager in a linux distribution. The following shows running and configuring things that require extra steps when setting it up:

* Installing Newman CLI with Node Package Manager
* Installing Newman-Reporter-Extra
* Downloading and running standalone Keycloak
* Configuring Keycloak
* Downloading and running standalone Jenkins
* Configuring Jenkins

### Installing Newman CLI with Node Package Manager
To be able to run postman collection tests, Newman is required:

In terminal

     npm install -g newman

### Installing newman-reporter-htmlextra
We use newman-reporter-htmlextra to see integration tests report in HTML format. This however requires Newman to be installed first.

In terminal

     npm install -g newman-reporter-extra

### Downloading and running Keycloak
Download [Keycloak Server](https://www.keycloak.org/downloads.html) then extract package when finished. Open terminal and navigate to the bin folder of the extracted package. For example:
   
     cd home/Desktop/keycloak-11.0.3/bin

then run the server:

     ./standalone.sh

By default, Keycloak uses port 8080 which coincidentally, used also by SpringBoot. To avoid conflicts, we will change what port Keycloak will run on. Navigate to `/keycloak-root/standalone/configuration/` and open `standalone.xml` and change http port to `9000`.

To start Keycloak, navigate to this address in your browser: 
    
    localhost:9000

### Configuring Keycloak
We will need to configure Keycloak for this project's authentication to work. For first time users, you will be prompted to make an admin user. 

Once an admin user account has been made, log in then create a new `realm` and name it `trackmapi`. Open `Clients` found on the menu, then once inside, hit `Create`.

Input the following values:

    Client ID: spring-boot-demo
    Client Protocol: openid-connect 
    Access Type: confidential 
    Valid Redirect URIs: http://localhost:8080/* 
    
    expand the Authentication Flow Overrides 
    
    Browser Flow: direct grant 
    Direct Grant Flow: direct grant

Open Credentials tab to get the secret value. Then click Roles from the Dashboard Menu then hit `Add Role`. Input user for the Role Name then save.

Going back to the Dashboard Menu, open `Users` under Manage. Click `Add User` then input `demouser` and save. Go to credentials tab and add a password, with temporary setting off. Then open Role Mappings and assign `user` from Available Roles to Assigned Roles 


### Downloading and running standalone Jenkins
Download standalone Jenkins from [here](https://www.jenkins.io/download/) and select Generic Java Package (.war) from either LTS or non LTS version

Navigate to the directory where jenkins was downloaded and run command
    
    $ -java -jar jenkins.war --httpPort=9090

Picking a different port is fine, but only if that port is unused.

### Configuring Jenkins
Launch a browser and access Jenkins through this URL
    
    localhost:9090

Where 9090 is the port we specified when running jenkins

You will be asked for a username and password. For username, write `admin` and for password, navigate to your `/home/.jenkins/secrets/` directory and open `initialAdminPassword.txt` then use what's written inside as the password.

### Jenkins Plugin
We need to install *HTML Publisher* plugin for our html reports to work. Open `Manage Plugins` from Dasboard, then `Manage Plugins`, then `Available` tab, and search `HTML Publisher`.

### Creating a Pipeline
From the dashboard, select `New Item`, enter a project name, and select `Pipeline`, then click `Ok`. We should be seeing 4 tabs namely General, Build Triggers, Advanced Project Options, and Pipeline. Select `Advanced Project Options` and pick `Pipeline from Script` from the dropdown under `Definition`. 

Select `Git` from under `SCM` dropdown menu and add this repository
    
    https://rbojos@bitbucket.org/mountainstatesoftware/rogelio-bojos-project.git

For the `Branch Specifier` in `Branches to Build`, copy and paste:
    
    */develop

Under `Script Path`:
    
    jenkinsfile 

Note: The Jenkins filename under Script Path must be the equal to the Jenkins filename from the repository's root directory. 

Once finished, hit `Save`.

### One last configuration
For some reason, the published HTML reports either won't display anything at all or be outright broken. (To do: confirm if CSS and security issue). To fix this, head back to the `Dashboard`, `Manage Jenkins`, `Script Console`, then copy and paste this line then run: 
    
    System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "") 



## __Running the Pipeline__
To run the pipeline, simply select `Build now` then wait for the pipeline to finish.

## __Viewing Reports__
Since we have the `HTML Plugin` addon installed, we should be able to view published HTML reports as seen from the same list where `Build now` is listed:
* Jacoco Report
* Postman Report
* JMeter Report

If the page shows a broken or empty formatting. Then running a specific command in the Script Console and running the pipeline again will fix this. See `One Last Configuration section` above.


## __Running the application after a successful Jenkins Build__
After a successful Jenkins pipeline build and push to Dockerhub. Navigate to the project directory `/TrackmAPI/src/main/resources/playtest2`

Before running docker-compose, we need to disable the running local mongodb to avoid port use conflicts:
    
     systemctl stop mongodb.service

then run the command in the terminal:
    
     docker-compose up

Addendum: If you need to enable MongoDB service again, run the command in terminal:
    
    systemctl start mongodb.service

This command will pull the trackmapi image from dockerhub, including official images for MongoDB and Keycloak. Once its finished pulling, it will then run these applications in a separate container. Fortunately, these containers are linked together due to how the docker-compose is configured.

For the dockerized Keycloak, the realm configuration from the local uncontainerized Keycloak has been imported to the containerized Keycloak during its creation. However, we still need to add users and assign roles as well as generate a new client secret key.

To access containerized Keycloak server, we can connect to:
    
    localhost:8080

Just like configuring the uncontainerized Keycloak we can:
>Open Credentials tab to get the secret value. Then click Roles from the Dashboard Menu then hit `Add Role`. Input user for the Role Name then save.
>
>Going back to the Dashboard Menu, open `Users` under Manage. Click `Add User` then input `demouser` and save. Go to credentials tab and add a password, with temporary setting off. Then open Role Mappings and assign `user` from Available Roles to Assigned Roles 

To access containerized Spring-boot application, we need to connect to:
    
    localhost:8081



## __Schema__
The figure below shows schema of my MongoDB collections

![schema](https://i.ibb.co/WKn26hk/trackmapi-nosql-schema.png)