## OSGi Sample


Ref: [Introduction to OSGi](https://www.baeldung.com/osgi)

Steps:
---

Download Apache Karaf from: <https://karaf.apache.org/download.html>
To install Karaf, you can follow the installation instruction from the [Official Documentation](https://karaf.apache.org/manual/latest/#_quick_start)

Download a binary distribution and unzip wherever you prefer.

Go to the location of repository and build the project using command: *mvn clean install*

Then run

    bin\karaf.bat start


### Bundle deploy

    bundle:install mvn:com.jng/osgi-intro-sample-activator/1.0-SNAPSHOT
    Bundle ID: 60
    
    bundle:install mvn:com.jng/osgi-intro-sample-service/1.0-SNAPSHOT
    Bundle ID: 61

    bundle:install mvn:com.jng/osgi-intro-sample-client/1.0-SNAPSHOT
    Bundle ID: 62

    >start 62
    >start 61
    Notification of service registered.
    Hello Jatin


Ref: [https://www.baeldung.com/osgi](https://www.baeldung.com/osgi)



