package com.genspark.foodordering.controller;

import com.genspark.foodordering.Service.AttachmentService;
import com.genspark.foodordering.Service.RestaurantService;
import com.genspark.foodordering.entity.Attachment;
import com.genspark.foodordering.entity.CurrencyConversion;
import com.genspark.foodordering.entity.MenuItem;
import com.genspark.foodordering.entity.Restaurant;
import com.genspark.foodordering.serrepository.MenuRepo;
import com.genspark.foodordering.serrepository.RestaurantRepo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
//@RequestMapping
@Slf4j
public class FoodController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private MenuRepo menuRepo;



    @PostMapping("/addRestaurant")
    public Restaurant saveRestaurant(@RequestParam("file") MultipartFile file,
                                     @RequestPart("restaurant") Restaurant restaurant) throws Exception {
        log.info("INSIDE saveRestaurant METHOD ****************************************************");
        restaurant.setAttachment(restaurantService.createAttachment(file));
        return restaurantService.saveRestaurant(restaurant);
        //restaurantService.saveRestaurant(restaurant);
        //return ResponseEntity.ok("Restaurant is registered");
       // return ResponseEntity.ok("Restaurant is registered");



    }
    //@GetMapping("/kafkaPublish/from/{from}/to/{to}}")
    //public CurrencyConversion convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable long quantity) {
      //  return kafkaCCProducerService.convertCurrency(from, to, quantity);
    //}

        @PostMapping("/addMenuItems")
    public MenuItem saveMenuItems(@RequestBody MenuItem menuItems){
        log.info("INSIDE inside menuitem METHOD ****************************************************");

        return menuRepo.save(menuItems);
            }

    @GetMapping("/getRestaurant/{id}")
    public Restaurant getRestaurant(@PathVariable String id){

        return restaurantService.getRestaurant(Long.parseLong(id));
    }

    @GetMapping("/getRestaurants")
    public List<Restaurant> getRestaurants(){

        return restaurantService.getRestaurants();
    }


    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.getAttachment(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }


    //@PostMapping("/addMenuItem")
    //public MenuItems addItem(@RequestBody MenuItems menuItem)
    //{
       // return  this.orderService.addItem(menuItem);
      //  return this.restaurantRepository.(menuItem);
    //}


    // get all restaurant
   // @GetMapping("/restaurant")
    //public ResponseEntity<List<Restaurant>> getAllRetaurant(@RequestParam(required = false) String name) {
      //  List<Restaurant> restaurants = new ArrayList<Restaurant>();

        //if (name == null)
          //  restaurantRepository.findAll().forEach(restaurants::add);
        //else
          //  restaurantRepository.findByName(name).forEach(restaurants::add);
        //if (restaurants.isEmpty()) {
          //  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //}
        //return new ResponseEntity<>(restaurants, HttpStatus.OK);
    //}

   // @GetMapping("/restaurants/{id}/")
    //public List<MenuItems> getRestaurantsMenuItems(@PathVariable("id") Long id){
      //  return restaurantRepository.findByMenuItems(id);

    //}
    //@PostMapping("/menuItem")
    //public List<MenuItems> getRestaurantsMenuItems(@PathVariable("id") Long id){
      //  return restaurantRepository.findByMenuItems(id);

    //}


   //   @GetMapping("/restaurants/{id}/menuitems")
 //   public ResponseEntity<MenuItems> getAllMenuItemsByRestaurantId(@PathVariable(value ="regId") long RegId){
     //   Restaurant restaurant = restaurantRepository.findById(id)
       //         .orElseThrow(()->new ResourceNotFoundException("Not found Restaurant with Id = + id"));
      //  List<MenuItems>menuItems = menuRepo.findByRestaurantId(regId);
        //  return new ResponseEntity<>(menuItems, HttpStatus.OK);

            //}

            //public ResponseEntity<Restaurant>  createRestaurant(@RequestBody Restaurant restaurant){
               // Restaurant _restaurant = restaurantRepository.save(new Restaurant(restaurant.getName(), restaurant.getAddress(), restaurant.getEmails(), restaurant.getRegdoc(),true))


            }


