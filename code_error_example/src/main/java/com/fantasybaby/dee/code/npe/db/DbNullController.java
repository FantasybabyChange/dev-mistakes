package com.fantasybaby.dee.code.npe.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @author reid.liu
 */
@RestController
@RequestMapping("dbnull")
@Slf4j
public class DbNullController {

    //    @Resource
    private UserRepository userRepository;
    //    @Resource
    private UserEntityRepository userEntityRepository;
    //    @Resource
    private UserScoreRepository userScoreRepository;

    /**
     * {
     * "name":"曦月",
     * "age":"13"
     * }
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public User save(@RequestBody User user) {
        user.setCreatedDate(new Date());
        return userRepository.save(user);
    }

    @RequestMapping(value = "/save/wrong", method = RequestMethod.POST)
    public User wrong(@RequestBody User user) {
        user.setNickName(String.format("guest%s", user.getName()));
        return userRepository.save(user);
    }

    @PostMapping("/update/right")
    public UserEntity right(@RequestBody UserDto user) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("用户Id不能为空");
        }
        UserEntity userEntity = userEntityRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("用户不存在"));

        if (user.getName() != null) {
            userEntity.setName(user.getName().orElse(""));
        }
        userEntity.setNickName("guest" + userEntity.getName());
        if (user.getAge() != null) {
            userEntity.setAge(user.getAge().orElseThrow(() -> new IllegalArgumentException("年龄不能为空")));
        }
        return userEntityRepository.save(userEntity);
    }

//    @PostConstruct
    public void init() {
        userScoreRepository.save(new UserScore());
    }

    @GetMapping("wrong")
    public void wrong() {
        log.info("result: {} {} {} ", userScoreRepository.wrong1(), userScoreRepository.wrong2(), userScoreRepository.wrong3());
    }

    @GetMapping("right")
    public void right() {
        log.info("result: {} {} {} ", userScoreRepository.right1(), userScoreRepository.right2(), userScoreRepository.right3());
    }
}
