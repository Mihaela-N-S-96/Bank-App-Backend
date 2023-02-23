package com.spring.test.SpringSecurity_JWT_test.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.spring.test.SpringSecurity_JWT_test.model.Account;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AccountSerializer extends JsonSerializer<Account> {

    @Override
    public void serialize(Account account, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
System.out.println("aaa");
        gen.writeNumberField("id", account.getId());
        gen.writeStringField("currency", account.getCurrency());
        gen.writeNumberField("savings", account.getSavings());
        gen.writeNumberField("deposit", account.getDeposit());
        gen.writeNumberField("balance", account.getBalance());
        gen.writeStringField("type_of_plan", account.getType_of_plan());
//        gen.writeStringField("created_at", account.getCreated_at().toString());
//        gen.writeObjectField("loans", account.getLoans());
//        gen.writeObjectField("saving", account.getSaving());

        gen.writeEndObject();
    }

    public List<Account> deserializeList(ArrayList<Account> accountArrayList){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Account.class, this);
        mapper.registerModule(module);
        String json = null;
        try {
            json = mapper.writeValueAsString(accountArrayList);
            System.out.println(json);
            List<Account> accounts = mapper.readValue(json, new TypeReference<List<Account>>(){});
            return accounts;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Account deserializeObject(Account accountObject){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Account.class, this);
        mapper.registerModule(module);
        String json = null;

//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        mapper.setDateFormat(dateFormat);
        try {
            json = mapper.writeValueAsString(accountObject);
            Account deserializedAccount = mapper.readValue(json, Account.class);

            return deserializedAccount;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
