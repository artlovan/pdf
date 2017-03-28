Simple service that will take PDF template in base64 (pdf that hold meta data)
and final flat PDF in base64 (meta data erased) and return user entered values from final pdf.
Return data is a map of pdf_field_name and user entered value.


Technologies wired up for use
-----------------------------
    1. Java
    2. Spring Boot


Docker Usage
------------
    1. Make sure docker is up & running
    2. cd into projects root dir (pdf dir)
    3. To start service, in terminal issue: docker-compose up -d
    4. To stop service, in terminal issue: docker-compose down


Check is service is up * running
--------------------------------
    Curl: curl localhost:8098/health | json_pp


Version: 0.1
------------