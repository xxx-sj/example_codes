package Junit_test.chap07.card_valid;

import Junit_test.chap07.card_valid.repository.AutoDebitInfoRepository;
import Junit_test.chap07.card_valid.req.AutoDebitReq;
import Junit_test.chap07.card_valid.validator.CardNumberValidator;

import java.time.LocalDateTime;

public class AutoDebitRegister {
    private CardNumberValidator validator;
    private AutoDebitInfoRepository repository;

    public AutoDebitRegister(CardNumberValidator validator,
                             AutoDebitInfoRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    public RegisterResult register(AutoDebitReq req) {
        //validator를 통해 해당 카드번호가 유효한지 체크
        CardValidity validity = validator.validate(req.getCardNumber());
        if(validity != CardValidity.VALID) {
            return RegisterResult.error(validity);
        }
        //db에서 카드정보를 찾아온다.
        AutoDebitInfo info = repository.findOne(req.getUserId());
        //있다면 업데이트 없다면 새로 등록하고, success를 반환한다.
        if(info != null) {
            info.changeCardNumber(req.getCardNumber());
        } else {
            AutoDebitInfo newInfo =
                    new AutoDebitInfo(req.getUserId(), req.getCardNumber(),
                            LocalDateTime.now());
            repository.save(newInfo);
        }

        return RegisterResult.success();
    }

}
