import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class TestBloomFilter {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379").setPassword("123qwe");

        // 构造redisson
        RedissonClient redissonClient = Redisson.create(config);

        // 初始化布隆
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("test_bloom_filter");
        bloomFilter.tryInit(10000000L, 0.03f);

        // 增加布隆项目
        bloomFilter.add("fatpo");

        // 测试布隆
        System.out.println("test fat in bloom filter:" + bloomFilter.contains("fat"));
        System.out.println("test po in bloom filter:" + bloomFilter.contains("po"));
        System.out.println("test fatpo in bloom filter:" + bloomFilter.contains("fatpo"));
    }
}
