package com.example.casemodule4group5.service.cartform;

import com.example.casemodule4group5.model.dto.CartForm;
import com.example.casemodule4group5.service.IGeneralService;

public interface ICartFormService extends IGeneralService<CartForm> {

    Iterable<CartForm> findAllCartForm();

    Iterable<CartForm> findAllCartFormByUserId(Long id);

}
