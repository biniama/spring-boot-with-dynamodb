#  Spring Boot With Dynamo DB Example  
This project shows how you can implement a Spring Boot API application that stores and reads data in a Dynamo DB with 2 tables (one table containing a list of other objects).  

## Project Information  

### Purpose
To be used as a stepping stone for other Spring Boot + Dynamo DB applications

### Developer
* Biniam Asnake <biniamasnake@gmail.com>

## DynamoDB Local
[Download Local version](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html)  
[Install AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-mac.html#cliv2-mac-install-confirm)    

Start Local Dynamo:   
    `java -Djava.library.path=./dynamodb_local -jar ~/dev-tools/dynamodb_local/DynamoDBLocal.jar -sharedDb`

Do:  
    ```
    aws configure
    AWS Access Key ID [None]: fakeMyKeyId
    AWS Secret Access Key [None]: fakeSecretAccessKey
    Default region name [None]: eu-west-01
    Default output format [None]:
    ```  

List tables available on DynamoDB:  
    `aws dynamodb list-tables --endpoint-url http://localhost:8000`

Dynamo UI:  
    ```
    npm install dynamodb-admin -g
    export DYNAMO_ENDPOINT=http://localhost:8000
    dynamodb-admin
    ```
    
  Browse to `http://localhost:8001/`

Running the app creates the tables for you. But, if you want to create Tables in Local DynamoDB (manually):  
    
    aws dynamodb create-table --table-name info --attribute-definitions AttributeName=Id,AttributeType=S --key-schema AttributeName=Id,KeyType=HASH --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 --endpoint-url http://localhost:8000    
    aws dynamodb create-table --table-name profile --attribute-definitions AttributeName=Id,AttributeType=S --key-schema AttributeName=Id,KeyType=HASH --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 --endpoint-url http://localhost:8000
    
Delete Table from Local DynamoDB:

    aws dynamodb delete-table --table-name info --endpoint-url http://localhost:8000
    aws dynamodb delete-table --table-name profile --endpoint-url http://localhost:8000
    
### Sample Request and Response
##### Create Content Request

`POST` `http://localhost:9898/content`
```json
{
  "contents": [
    {
      "contentId": "83c1b410-ec14-4c2e-b165-afbf98f59627",
      "value": 0,
      "profileId": null,
      "description": "description",
      "placements": [
        {
          "features": "feature",
          "category": [
            "category1",
            "category2"
          ],
          "visible": true,
          "value": 100
        }
      ],
      "expirationDate": "2021-08-06 15:01:57"
    }
  ]
}
```

##### Response
```json
[
  {
    "profile": {
      "id": "profile::45a496b4-69b8-4889-912d-f3ba474d0109",
      "description": "description",
      "mappings": [
        {
          "features": "feature",
          "category": [
            "category1",
            "category2"
          ],
          "visible": true,
          "value": 100
        }
      ],
      "lastUpdatedDate": "2021-08-31 14:22:40"
    },
    "info": {
      "id": "info::83c1b410-ec14-4c2e-b165-afbf98f59627",
      "value": 0,
      "profileId": "profile::45a496b4-69b8-4889-912d-f3ba474d0109",
      "expirationDate": "2021-08-06 15:01:57",
      "lastUpdatedDate": "2021-08-31 14:22:40"
    }
  }
]
```
