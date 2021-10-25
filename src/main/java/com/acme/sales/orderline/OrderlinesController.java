package com.acme.sales.orderline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/api/orderlines")
public class OrderlinesController {
		@Autowired
		private OrderlineRepository olRepo;
		@GetMapping
		public ResponseEntity<Iterable<Orderline>> GetAll(){
			var orderlines = olRepo.findAll();
			return new ResponseEntity<Iterable<Orderline>>(orderlines,HttpStatus.OK);
		}
		
		@GetMapping("{id}")
		public ResponseEntity<Orderline> GetById(@PathVariable int id){
			var orderline = olRepo.findById(id);
			if(orderline.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Orderline>(orderline.get(),HttpStatus.OK);
		}
		
		@PostMapping
		public ResponseEntity<Orderline> Insert(@RequestBody Orderline orderline){
			if(orderline == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			orderline.setId(0);
			var newOrderline = olRepo.save(orderline);
			return new ResponseEntity<Orderline>(newOrderline,HttpStatus.OK);
		}
		
		@SuppressWarnings("rawtypes")
		@PutMapping("{id}")
		public ResponseEntity Update(@PathVariable int id, @RequestBody Orderline orderline) {
			if(orderline.getId()!=id) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			var oldOrderline = olRepo.findById(orderline.getId());
			if(oldOrderline.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			olRepo.save(orderline);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		@SuppressWarnings("rawtypes")
		@DeleteMapping("{id}")
		public ResponseEntity Delete(@PathVariable int id) {
			var orderline = olRepo.findById(id);
			if(orderline.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			olRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
