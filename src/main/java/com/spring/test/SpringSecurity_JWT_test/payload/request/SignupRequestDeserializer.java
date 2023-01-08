//package com.spring.test.SpringSecurity_JWT_test.payload.request;
//
//import com.fasterxml.jackson.core.JacksonException;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
//import com.spring.test.SpringSecurity_JWT_test.model.Account;
//import com.spring.test.SpringSecurity_JWT_test.model.UserDetail;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class SignupRequestDeserializer extends StdDeserializer<SignupRequest> {
//
//    public SignupRequestDeserializer(){
//        this(null);
//    }
//
//    public SignupRequestDeserializer(Class<?> vc){
//        super(vc);
//    }
//
//    @Override
//    public SignupRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
//
//        JsonNode node = p.getCodec().readTree(p);
//        String username = node.get("username").asText();
//        String password = node.get("password").asText();
//        String email = node.get("email").asText();
//
//        JsonNode userDetailNode = node.get("userDetail");
//        String first_name = userDetailNode.get("first_name").asText();
//        String last_name = userDetailNode.get("last_name").asText();
//        String country = userDetailNode.get("country").asText();
//
//        JsonNode birthdayNode = userDetailNode.get("birthday");
//        String birthdayString = birthdayNode.asText();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date birthday;
//        try{
//            birthday = format.parse(birthdayString);
//        }catch (ParseException e){
//            throw new RuntimeException(e);
//        }
//
//        String address = userDetailNode.get("address").asText();
//        String gender = userDetailNode.get("gender").asText();
//        String mobile = userDetailNode.get("mobile").asText();
//
//        JsonNode created_atNode = userDetailNode.get("created_at");
//        String created_atString = created_atNode.asText();
//        Date created_at;
//        try{
//            created_at = format.parse(created_atString);
//        }catch (ParseException e){
//            throw new RuntimeException(e);
//        }
//
//        UserDetail userDetail = new UserDetail(first_name, last_name, country, birthday, address, gender, mobile, created_at);
//
//        JsonNode userAccountNode = node.get("account");
//        List<Account> userAccounts = new ArrayList<>();
//        if(userAccountNode.isArray()){
//            for(JsonNode account: userAccountNode){
//                String currency = account.get("currency").asText();
//                Double savings = account.get("savings").asDouble();
//                Double balance = account.get("balance").asDouble();
//                String type_of_plan = account.get("type_of_plan").asText();
//
//                JsonNode created_atAccountNode = account.get("created_at");
//                String created_atAccountString = created_atAccountNode.asText();
//                SimpleDateFormat formatAccount = new SimpleDateFormat("yyyy-MM-dd");
//                Date created_atAccount;
//
//                try{
//                    created_atAccount = formatAccount.parse(created_atAccountString);
//                }catch (ParseException e){
//                    throw new RuntimeException(e);
//                }
//                userAccounts.add(new Account(currency, savings, balance, type_of_plan, created_atAccount ));
//            }
//        }
//
//        return new SignupRequest(username, email, password, userDetail, userAccounts);
//    }
//}
