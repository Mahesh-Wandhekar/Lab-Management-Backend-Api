package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Bind.KdnBind;
import com.example.demo.Entity.KdnEntity;
import com.example.demo.Service.KdnServices;

@RestController
public class KdnController {

	@Autowired
	private KdnServices kdnServices;

	@PostMapping("/action/{choise}")
	public ResponseEntity<?> handleAction(@PathVariable("choise") String choice, @RequestBody KdnBind kdnbind) {
		switch (choice.toLowerCase()) {
		case "register":
			boolean status = kdnServices.reg(kdnbind);
			return status ? ResponseEntity.ok("Account registered.")
					: ResponseEntity.status(500).body("Registration failed.");

		case "search":
			KdnEntity entity = kdnServices.search(kdnbind);
			if (entity != null) {
				return ResponseEntity.ok(entity);
			} else {
				return ResponseEntity.status(404).body("Account not found.");
			}

		case "remove":
			boolean deleted = kdnServices.delete(kdnbind);
			return deleted ? ResponseEntity.ok(" Account deleted.")
					: ResponseEntity.status(500).body("Account deletion failed.");

		default:
			return ResponseEntity.badRequest().body("Invalid choice");
		}
	}
}
