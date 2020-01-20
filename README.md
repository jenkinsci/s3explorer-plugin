# The Jenkins plugin for working with an Amazon S3 bucket.

## Configure
Go to _Manage Jenkins_ -> _Configure System_  

Input required parameters:  
![image](https://user-images.githubusercontent.com/1695690/63889367-cf28dc00-ca1b-11e9-9b55-6a2337c51b21.png)  

Press Save button

## Usage
### First, you need to create a Jenkins project like this  
![image](https://user-images.githubusercontent.com/1695690/63889705-7ad22c00-ca1c-11e9-8a46-2a1415e18048.png)


### When you open the `testproject`, you will see `S3 Explorer` links like this  
![image](https://user-images.githubusercontent.com/1695690/63889850-cf75a700-ca1c-11e9-803f-dc0a095fa5c7.png)

### Click on either link to open your S3 bucket  
![image](https://user-images.githubusercontent.com/1695690/63890224-75291600-ca1d-11e9-8e60-d658f09f70dd.png)

Here, you can upload/delete files to your S3 bucket.

## Leveraging Open Source
This Jenkins plugin uses source code from https://github.com/awslabs/aws-js-s3-explorer/tree/v2-alpha
