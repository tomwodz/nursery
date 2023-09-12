package pl.tomwodz.nursery.domain.user;

import org.apache.commons.codec.digest.DigestUtils;
import pl.tomwodz.nursery.domain.address.Address;
import pl.tomwodz.nursery.domain.user.dto.UpdateUserRequestDto;
import pl.tomwodz.nursery.domain.user.dto.UserRequestDto;

class UserFactory {

    User mapFromUserRequestDtoToUser(UserRequestDto requestDto) {
        return new User(
                requestDto.login(),
                DigestUtils.md5Hex(requestDto.password()),
                requestDto.name(),
                requestDto.surname(),
                requestDto.email(),
                requestDto.phoneNumber(),
                new Address(
                        requestDto.street(),
                        requestDto.zipCode(),
                        requestDto.city()));
    }

    User mapFromUpdateUserRequestDtoToUser(Long id, UpdateUserRequestDto requestDto) {
        return new User(
                id,
                requestDto.name(),
                requestDto.surname(),
                requestDto.email(),
                requestDto.phoneNumber(),
                new Address(id,
                        requestDto.street(),
                        requestDto.zipCode(),
                        requestDto.city()));
    }

}
