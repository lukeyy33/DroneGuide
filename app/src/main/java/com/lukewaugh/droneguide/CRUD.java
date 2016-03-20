package com.lukewaugh.droneguide;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;



/**
 * Created by Luke on 20/03/16.
 */
@DynamoDBTable(tableName = "Users")
public class CRUD {
    Context c;
    public CRUD(Context context) {
        c=context;

    }
    public CRUD(){}

    CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
            c,
            "us-east-1:4bb5f85b-f014-4f7b-a2af-1dcaad5dd012", // Identity Pool ID
            Regions.US_EAST_1); // Region

    AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);


    //CognitoSyncManager cognitoSyncManager = new CognitoSyncManager();
    //CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider

    //AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
    DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);




    private String userID;
    private String password;

    CRUD crud = new CRUD();

    @DynamoDBIndexRangeKey(attributeName = "ID")
    public String getName() {
        return userID;
    }

    @DynamoDBAttribute(attributeName = "Password")
    public String getPassword() {
        return password;
    }

    public String setName(String username) {
        return this.userID = username;
    }
    public String setPassword(String password) {
        return this.password = password;
    }


}
