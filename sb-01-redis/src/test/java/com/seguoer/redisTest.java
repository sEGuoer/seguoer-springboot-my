package com.seguoer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.UUID;
@SpringBootTest
public class redisTest {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    @DisplayName("opsForValue()拿到key对应的value值，记得启动redis的img(映像)")
    void string() {
        String key = "key-for-string";
        String value = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(key, value);

        Assertions.assertEquals(value, redisTemplate.opsForValue().get(key));
    }

    @Test
    @DisplayName("opsForList,为list服务的方法，注意这里leftpush添加的顺序")
    void list() {
        String key = "key-for-list";

        redisTemplate.opsForList().leftPush(key, "X");
        redisTemplate.opsForList().leftPush(key, "Y");
        redisTemplate.opsForList().leftPush(key, "Z");

        Assertions.assertEquals("X", redisTemplate.opsForList().rightPop(key));
    }

    @Test
    @DisplayName("opsForSet,为set服务的方法，不会重复添加")
    void set() {
        String key = "key-for-set";

        redisTemplate.opsForSet().add(key, "X", "Y", "Z", "Z");

        Assertions.assertEquals(3, redisTemplate.opsForSet().size(key));
    }

    @Test
    @DisplayName("opsForSet,为set服务的方法，不会重复添加,会给对应的value赋予分数，如果有重复后面为最终结果（可以理解为替换）")
    void zset() {
        String key = "key-for-zset";

        redisTemplate.opsForZSet().add(key, "Alice", 86.08);
        redisTemplate.opsForZSet().add(key, "Bob", 98.35);
        redisTemplate.opsForZSet().add(key, "Bob", 99.35);
        redisTemplate.opsForZSet().add(key, "Trudy", 92.56);

        ZSetOperations.TypedTuple<String> popMax = redisTemplate.opsForZSet().popMax(key);

        Assertions.assertEquals("Bob", popMax.getValue());
        Assertions.assertEquals(99.35, popMax.getScore());
    }

    @Test
    @DisplayName("opsForHash,类似hashmap")
    void hash() {
        String key = "key-for-hash";

        redisTemplate.opsForHash().put(key, "key1", "value1");
        redisTemplate.opsForHash().put(key, "key2", "value2");
        redisTemplate.opsForHash().put(key, "key3", "value3");

        Assertions.assertEquals("value2", redisTemplate.opsForHash().get(key, "key2"));
    }
}
