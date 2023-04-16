package com.genspark.foodordering.Service;

import com.genspark.foodordering.Util.AppConstants;
import com.genspark.foodordering.Util.Utility;
import com.genspark.foodordering.entity.Attachment;
import com.genspark.foodordering.entity.CurrencyConversion;
import com.genspark.foodordering.entity.MenuItem;
import com.genspark.foodordering.entity.Restaurant;
import com.genspark.foodordering.serrepository.MenuRepo;
import com.genspark.foodordering.serrepository.RestaurantRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RestaurantService implements RestaurantServiceInt{

    @Autowired
    private RestaurantRepo restaurantRepo;
    @Autowired
    private MenuRepo menuRepo;

    private static Logger LOGGER= LoggerFactory.getLogger(RestaurantService.class);
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    public RestaurantService(){

    }
  //  Order addOrder(Order order);

    //@Override
    //public Order addOrder(Order order) {
      //  return this.orderDao.save(order);

    public MenuItem saveMenuItems(MenuItem menuItems) {
        return this.menuRepo.save(menuItems);
    }
    public CurrencyConversion convertCurrency(String from, String to) {


        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        String url = "http://localhost:9001/addRestaurant/from/{from}/to/{to}";
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(url, CurrencyConversion.class, uriVariables);
        CurrencyConversion response = responseEntity.getBody();
       // double totalAmount = response.getConversionRate() * quantity;
        CurrencyConversion currencyConversion = new CurrencyConversion(response.getId(), from, to, response.getPort());
        LOGGER.info(String.format("Message sent->%s", currencyConversion));
        Message<CurrencyConversion> message = MessageBuilder.withPayload(currencyConversion).setHeader(KafkaHeaders.TOPIC, AppConstants.TOPIC_NAME)
                .build();
        kafkaTemplate.send(message);
        return currencyConversion;
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        Optional<Restaurant> op = restaurantRepo.findById(id);
        Restaurant restaurant = null;
        if(op.isPresent()){
            restaurant = op.get();
            Utility.changeOrderAttachmentToURL(restaurant);
        }

        return restaurant;
    }

    @Override
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        for(Restaurant r : restaurants){
            r = Utility.changeOrderAttachmentToURL(r);
        }
        return restaurants;
    }

    public Restaurant saveRestaurant(Restaurant restaurant){
        LOGGER.info(String.format("Restauranr is register->%s", restaurant.toString()));
       // kafkaPublish.sendAddMessage(user);
        //return ResponseEntity.ok("Restaurant is registered");
        //kafkaPublish.sendAddMessage(user);
        //return ResponseEntity.ok("Restaurant is registered");
        //restaurantRepo.save(restaurant);
        //return ResponseEntity.ok("Restaurant is registered");
       // Message<Restaurant> message = MessageBuilder.withPayload(restaurant).setHeader(KafkaHeaders.TOPIC, AppConstants.TOPIC_NAME)
         //       .build();
        //kafkaTemplate.send(message);

        return this.restaurantRepo.save(restaurant);
        //Message<User> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, AppConstants.TOPIC_NAME).build();
        //kafkaTemplate.send(message);
        //return this.restaurantRepo.save(restaurant);

    }
   // public void sendAddMessage() {
     //   LOGGER.info(String.format("Restauranr is register->%s");
       // Message message = MessageBuilder
        //.KafkaHeaders.TOPIC, AppConstants.TOPIC_NAME
          //      .build();
        //kafkaTemplate.send(message);
    //}
    //LOGGER.info(String.format("Restauranr is register->%s", data.toString()));
    //Message<User> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, AppConstants.TOPIC_NAME).build();
      //  kafkaTemplate.send(message);


    public Attachment createAttachment(MultipartFile file) throws Exception{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            Attachment attachment
                    = new Attachment(fileName,
                    file.getContentType(),
                    file.getBytes());

            return attachment;

        } catch (Exception e) {
            throw new Exception("Could not create Attachment: " + fileName);
        }
    }

    //public MenuItems addItems(MenuItems menuItems) {
      //  return this.menuRepo.save(menuItems);
    //}



}
