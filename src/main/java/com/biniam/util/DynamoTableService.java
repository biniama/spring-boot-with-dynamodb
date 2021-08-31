package com.biniam.util;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.biniam.config.DynamoDBConfig;
import com.biniam.entity.Info;
import com.biniam.entity.Profile;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Log
@Import({PropertyPlaceholderAutoConfiguration.class, DynamoDBConfig.class})
public class DynamoTableService {

    private final AmazonDynamoDB amazonDynamoDB;

    private final DynamoDBMapper mapper;

    private boolean isTestTableCreated;

    private List<Class> modelClasses;

    @Autowired
    public DynamoTableService(DynamoDBMapper mapper, AmazonDynamoDB amazonDynamoDB) {
        this.mapper = mapper;
        this.amazonDynamoDB = amazonDynamoDB;

        modelClasses = new ArrayList<>();
        modelClasses.add(Info.class);
        modelClasses.add(Profile.class);
    }

    public void initializeDynamoDbTables() throws Exception {

        for (Class clazz : modelClasses) {
            createTable(clazz);
        }

        log.info("Current DynamoDB tables are: ");
        listDynamoDbTables().forEach(log::info);
    }

    public void deleteDynamoDbTables() {

        for (Class clazz : modelClasses) {
            deleteTable(clazz);
        }
    }

    private void createTable(Class clazz) throws Exception {

        log.info("Creating DynamoDB table for " + clazz.getSimpleName());

        CreateTableRequest ctr = mapper
                .generateCreateTableRequest(clazz)
                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

        isTestTableCreated = TableUtils.createTableIfNotExists(amazonDynamoDB, ctr);

        if (isTestTableCreated) {
            log.info("Created DynamoDB table for " + ctr.getTableName());
        } else {
            log.info("Table already exists for " + ctr.getTableName());
        }

        TableUtils.waitUntilActive(amazonDynamoDB, ctr.getTableName());

        log.info("Table " + ctr.getTableName() + " is active");
    }

    private void deleteTable(Class clazz) {

        if (isTestTableCreated) {

            DeleteTableRequest dtr = mapper.generateDeleteTableRequest(clazz);
            TableUtils.deleteTableIfExists(amazonDynamoDB, dtr);
            log.info("Deleted table " + dtr.getTableName());
        }
    }

    private List<String> listDynamoDbTables() {

        ListTablesResult tablesResult = amazonDynamoDB.listTables();

        return new ArrayList<>(tablesResult.getTableNames());
    }
}
