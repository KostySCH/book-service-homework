package ru.neoflex.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.neoflex.presentation.dto.AccountDto;
import ru.neoflex.presentation.dto.CreateAccountRequest;
import ru.neoflex.presentation.model.Account;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    AccountDto toDto(Account account);

    Account toEntity(CreateAccountRequest createAccountRequest);
}
