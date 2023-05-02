package com.spring.test.SpringSecurity_JWT_test.controller;

import com.spring.test.SpringSecurity_JWT_test.model.Withdrawal;
import com.spring.test.SpringSecurity_JWT_test.service.WithdrawalService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.spring.test.SpringSecurity_JWT_test.controller.AuthController.CSRF_TOKEN_HEADER_NAME;

@RestController
@RequestMapping("/withdrawals")
public class WithdrawalController extends CsrfController{

    private WithdrawalService withdrawalService;

    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @PatchMapping("/withdrawal")
    public Withdrawal addWithdrawal(@RequestBody Withdrawal withdrawal,
                                    @RequestParam Integer id,
                                    @RequestHeader(CSRF_TOKEN_HEADER_NAME) String csrfToken,
                                    HttpSession session)throws Exception{

        validateCsrfToken(csrfToken, session);
        withdrawal = withdrawalService.saveWithdrawal(withdrawal, id);


        return  withdrawal;

    }
}
