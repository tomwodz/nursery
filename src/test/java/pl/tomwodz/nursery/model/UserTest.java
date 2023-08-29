package pl.tomwodz.nursery.model;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void initializerUser() {
        user = new User();
    }

    @Test
    void newlyCreatedUserShouldHaveRoleParent() {

        //given

        //when
        //then
        assertThat(user.getRole(), is(User.Role.PARENT));
    }

    @Test
    void newlyCreatedUserShouldBeActive() {

        //given

        //when
        //then
        assertTrue(user.isActive());
    }

    @Test
    void newlyCreatedUserShouldNotHaveAnyChildren() {

        //given

        //when
        //then
        assertNull(user.getChild());
    }

    @Test
    void userShouldHaveOneOfThreeRoles() {

        //given

        //when
        //then
        assertThat(user.getTypeOfRole(), Matchers.hasSize(3));
    }

}