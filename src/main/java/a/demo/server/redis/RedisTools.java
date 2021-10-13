package a.demo.server.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public final class RedisTools {
    @Autowired
    private RedisTemplate<String,Object>redisTemplate;

    public Object get(final String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    public boolean set(final String key,final Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean set(final String key,final Object value,final Long time){
        return set(key,value,time,TimeUnit.SECONDS);
    }
    public boolean set(final String key,final Object value,final Long time,final TimeUnit timeUnit){
        try {
            if (time>0){
                redisTemplate.opsForValue().set(key,value,time,timeUnit);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean hasKey(final String key){
        return key==null?Boolean.FALSE:redisTemplate.hasKey(key);
    }

    public void delete(final String key){
        if (hasKey(key)){
            redisTemplate.delete(key);
        }
    }
    public void delete(final String ... keys){
        for (String key:keys){
            delete(key);
        }
    }

    public void deletePattern(final String pattern){
        Set<String>keys=redisTemplate.keys(pattern);
        if (keys!=null){
            if (keys.size()>0){
                redisTemplate.delete(keys);
            }
        }
    }

    public Long getExpire(final String key){
        return getExpire(key,TimeUnit.SECONDS);
    }
    public Long getExpire(final String key,final TimeUnit timeUnit){
        return redisTemplate.getExpire(key,timeUnit);
    }

    public boolean expire(final String key,final Long time){
        return expire(key,time,TimeUnit.SECONDS);
    }
    public boolean expire(final String key,final Long time,final TimeUnit timeUnit){
        try {
            if (time>0){
                redisTemplate.expire(key,time,timeUnit);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
