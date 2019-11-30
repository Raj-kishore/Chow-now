package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Dish;
import com.example.demo.service.DishService;

@CrossOrigin(origins = "https://phorons.com", allowedHeaders = "*")
@RestController
public class DishController {
	// Save the uploaded file to this folder
	// private static String UPLOADED_FOLDER =
	// "/home/rajkishor/Desktop/foodali/modern-foodies-india/src/assets/images/";
	private static String UPLOADED_FOLDER = "/home/mrcgddmy/public_html/assets/images/";

	@Autowired
	private DishService dishService;

	@RequestMapping(value = "/createDish", method = RequestMethod.POST)
	public Dish create(@RequestBody(required = false) Dish dish) {
		Dish d = dishService.create(dish);
		return d;
	}

	@CrossOrigin(allowCredentials = "true", origins = { "*" }, allowedHeaders = "*", methods = { RequestMethod.GET,
			RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
	@PostMapping("/upload") // //new annotation since 4.3
	public String singleFileUpload(@RequestParam("myFile") MultipartFile file,
			@RequestParam("fileData") String fileData, RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + fileData);
//            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/uploadStatus";
	}

	@CrossOrigin(allowCredentials = "true", origins = { "*" }, allowedHeaders = "*", methods = { RequestMethod.GET,
			RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}

	@RequestMapping(value = "/getAlldishes", method = RequestMethod.GET)
	public List<Dish> getAllDishesInTenKm(Double lat, Double lng, String filterType) {
		List<Dish> SpecificDishes = new ArrayList<>(); // list of dishes in 10 km range
		if (filterType == "") {
			List<Dish> Dishes = dishService.getAll();
			for (int i = 0; i < Dishes.size(); i++) {
				Double slat = Dishes.get(i).getLat();
				Double slng = Dishes.get(i).getLng();
				// ref for getting distance between two points on a sphere :
				// https://stackoverflow.com/a/3694410
				// Using Haversine equation to calculate orthodromic distance on sphere with two
				// points having lat and lng
				// System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'M') + "
				// Miles\n");
				// System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'K') + "
				// Kilometers\n");
				// System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'N') + "
				// Nautical Miles\n");
				Double dist = distance(lat, lng, slat, slng, 'K'); // //get distance between user and dish
				if (dist < 10) { // if dish is under 10 km range, add dish to specific dishes
					SpecificDishes.add(Dishes.get(i));
				}
			}
		} else if (filterType == "allVeg") {
			List<Dish> Dishes = dishService.getAllByVeg();
			for (int i = 0; i < Dishes.size(); i++) {
				Double slat = Dishes.get(i).getLat();
				Double slng = Dishes.get(i).getLng();
				Double dist = distance(lat, lng, slat, slng, 'K');
				if (dist < 10) {
					SpecificDishes.add(Dishes.get(i));
				}
			}
		} else if (filterType == "allDelivery") {
			List<Dish> Dishes = dishService.getAllByDelivery();
			for (int i = 0; i < Dishes.size(); i++) {
				Double slat = Dishes.get(i).getLat();
				Double slng = Dishes.get(i).getLng();
				Double dist = distance(lat, lng, slat, slng, 'K');
				if (dist < 10) {
					SpecificDishes.add(Dishes.get(i));
				}
			}
		}
		return SpecificDishes;
	}

	private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == 'K') {
			dist = dist * 1.609344;
		} else if (unit == 'N') {
			dist = dist * 0.8684;
		}
		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

	@RequestMapping(value = "/getDishById", method = RequestMethod.GET)
	public Optional<Dish> findById(@RequestParam(required = true) String id) {

		return dishService.findById(id);
	}

	@RequestMapping(value = "/getDishesByuId", method = RequestMethod.GET)
	public List<Dish> findByuId(@RequestParam(required = true) String uid) {
//ref 
//https://spring.io/blog/2014/07/17/text-search-your-documents-with-spring-data-mongodb
//	https://github.com/spring-projects/spring-data-examples/tree/master/mongodb/text-search/src/main/java/example/springdata/mongodb
//		
		return dishService.findByuId(uid);
	}

	@RequestMapping(value = "/getDishesBySearch", method = RequestMethod.GET)
	public List<Dish> findBySearch(@RequestParam(required = true) String q) {

		return dishService.findBySearch(q);
	}

	@RequestMapping(value = "/updateDishById", method = RequestMethod.PUT)
	public String update(@RequestParam String id, @RequestBody Dish dish) {
		Optional<Dish> internaOptionallDish = dishService.findById(id);
		Dish internalDish = internaOptionallDish.get();

		String userid = internalDish.getuId();
		String cuisine = internalDish.getCuisine();

		dish.setCuisine(cuisine);
		dish.setId(id);
		dish.setuId(userid);
		dish.setDishName(dish.getDishName());
		dish.setNoOfServings(dish.getNoOfServings());
		dish.setFoodDescription(dish.getFoodDescription());
		dish.setIsVeg(dish.getIsVeg());
		dish.setLat(dish.getLat());
		dish.setLng(dish.getLng());
		dish.setDelivery(dish.getDelivery());

		Dish d = dishService.update(dish);

		return d.toString();
	}

	@RequestMapping(value = "/publishDishById", method = RequestMethod.PUT)
	public String updateP(@RequestParam String id, @RequestBody Dish dish) {
		Optional<Dish> internaOptionallDish = dishService.findById(id);
		Dish internalDish = internaOptionallDish.get();

//		private String id;
//		private String uId;
//		@TextIndexed(weight=2) private String dishName;
//		private String noOfServings;
//		@TextIndexed private String foodDescription;
//		private Boolean publishStatus;
//		private String isVeg;
//		private int serialNo;
//		private String address;
//		private String delivery;
//		private String cuisine;
//		private MultipartFile image;
//		private boolean request;

		String userid = internalDish.getuId();
		String dishName = internalDish.getDishName();
		String noOfServings = internalDish.getNoOfServings();
		String foodDesc = internalDish.getFoodDescription();
		String isVeg = internalDish.getIsVeg();

		Double lat = internalDish.getLat();
		Double lng = internalDish.getLng();

		String delivery = internalDish.getDelivery();
		String cuisine = internalDish.getCuisine();
		dish.setCuisine(cuisine);
		dish.setId(id);
		dish.setuId(userid);
		dish.setDishName(dishName);
		dish.setNoOfServings(noOfServings);
		dish.setFoodDescription(foodDesc);
		dish.setIsVeg(isVeg);
		dish.setLat(lat);
		dish.setLng(lng);
		dish.setDelivery(delivery);

		dish.setPublishStatus(dish.getPublishStatus());

		Dish d = dishService.update(dish);

		return d.toString();
	}

	@RequestMapping(value = "/deleteAllDishes", method = RequestMethod.DELETE)
	public String deleteAll() {
		dishService.deleteAll();
		return "Deleted all records";
	}

}
//[1] : https://examples.javacodegeeks.com/enterprise-java/spring/boot/spring-boot-mongodb-crud-operations-example/