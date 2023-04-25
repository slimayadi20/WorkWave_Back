package com.example.workwave.controllers;


import com.example.workwave.entities.JwtRequest;
import com.example.workwave.entities.JwtResponse;
import com.example.workwave.entities.User;
import com.example.workwave.repositories.UserRepository;
import com.example.workwave.services.JwtService;
import com.example.workwave.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin
public class JwtController {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping({"/authenticate"})
    public ResponseEntity<?> createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println("request");
        System.out.println(jwtRequest.getUserName());
        System.out.println(jwtRequest.getPassword());
        return jwtService.createJwtToken(jwtRequest);
    }
    /*
    @PostMapping("/auth/face")
    public ResponseEntity<?> authenticateWithFace(@RequestParam("username") String username,
                                                  @RequestParam("image") MultipartFile image) throws Exception {

        Optional<User> userOptional = userRepository.findById(username);

        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOptional.get();

        IFaceClient faceClient = new FaceClientBuilder()
                .endpoint(user.getFaceApiEndpoint())
                .credential(new AzureKeyCredential(user.getFaceApiKey()))
                .buildClient();

        // Detect faces in the login image
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image.getBytes());
        DetectResult[] detectResults = faceClient.face().detectInStream()
                .withImage(imageStream)
                .withDetectionModel(DetectionModel.DETECTION_02)
                .execute();

        if (detectResults.length != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid image: Only one face must be present.");
        }

        // Verify the detected face against the user's face ID
        String detectedFaceId = detectResults[0].faceId().toString();
        VerifyResult verifyResult = faceClient.face().verify()
                .faceId(detectedFaceId)
                .faceId2(user.getFaceId())
                .execute();

        if (verifyResult.isIdentical() && verifyResult.confidence() >= CONFIDENCE_THRESHOLD) {
            // If faces match, create a token

            System.out.println("user  logged in");
            UserDetails userDetails = jwtService.loadUserByUsername(username);

            String newGeneratedToken = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(user, newGeneratedToken));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Face verification failed.");
        }
    }
}*/


}
