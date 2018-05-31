import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Auther: curry
 * @Date: 2018/5/31 23:04
 * @Description:
 */

public class Test {
    @org.junit.Test
    public void demo1(){
        Jedis jedis = new Jedis("192.168.142.128",6379);
        jedis.set("name", "test");
        String name = jedis.get("name");
        System.err.println(name);
        jedis.close();

    }

    @org.junit.Test
    public void demo2(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(30);
        config.setMaxIdle(10);
        JedisPool jedisPool = new JedisPool(config,"192.168.142.128",6379);
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set("name", "毛毛");
            String value = jedis.get("name");
            System.out.println(value);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
            if(jedisPool != null){
                jedisPool.destroy();
            }
        }
    }

}
