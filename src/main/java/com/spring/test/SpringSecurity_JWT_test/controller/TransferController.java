package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Transfer;
import com.spring.test.SpringSecurity_JWT_test.repository.TransferRepository;
import com.spring.test.SpringSecurity_JWT_test.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static com.spring.test.SpringSecurity_JWT_test.controller.AuthController.CSRF_TOKEN_HEADER_NAME;


@RestController
@RequestMapping("/transfers")
public class TransferController extends CsrfController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/")
    public ArrayList<Transfer> getAllTransfers(@RequestParam Integer id,
                                               @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                               HttpSession session)throws Exception{

        validateCsrfToken(csrfToken, session);
        return  transferService.getAllAccountIdTransfers(id);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Object> addTransfer(@RequestBody Transfer transfer,
                                              @RequestParam Integer id,
                                              @RequestParam String email,
                                              @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                              HttpSession session)throws Exception {

        validateCsrfToken(csrfToken, session);
        return  new ResponseEntity<>(transferService.getTransferResponse(
                                    transfer, id, email
        ), HttpStatus.OK);
    }
}
