package com.zscalerlabsession.zscalerlabsession.serviceimpl;

import com.zscalerlabsession.zscalerlabsession.Model.Account;
import com.zscalerlabsession.zscalerlabsession.Model.Customer;
import com.zscalerlabsession.zscalerlabsession.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
@Service
public class ValidationServiceImpl implements ValidationService
{

    @Override
    public boolean emailValidation(Customer customer)
    {
        String email_id = customer.getEmailId();
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (email_id!=null)
        {
            if(Pattern.compile(regexPattern).matcher(email_id).matches())
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean accountNumberValidation(Account account)
    {
        long accNumber = account.getAccountNumber();
        int leng = Long.toString(accNumber).length();
        if(leng >= 12 && leng <= 14){
            return true;
        }
        return false;
    }
}
