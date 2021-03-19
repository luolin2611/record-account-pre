package cn.recordaccount.common.util;

import cn.recordaccount.common.constant.ResStatusEnum;
import cn.recordaccount.common.dto.Response;
import cn.recordaccount.common.entity.User;
import io.jsonwebtoken.*;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 * @author luolin
 * @date 2021-03-01 17:02:13
 */
public class JwtUtils {
    private static String JWT_KEY_ID = "id";
    private static String JWT_KEY_USER_NAME = "rollin";

    private static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtxJuPRMyxZF7rSWBi6JY6ULSWo+/l2gd+Ap7a6VVuSHWGM8PQaVNmXKp2L1ythAAlSJL5Xjv/ZWI89UST3u9OGB8GcYatcn0Znjej+Ju2yaXPu8s5SEfnlOGgxbIlhOxQVk4YYk85/jOHgKQ2Hm5YIL1KEmDdpLvAPAVWTiPU8tNjNAoEMFQNfZKuI/cJsn8jHfsWXwXtcXTzwcVT3WJiNCOEzmaED0UsMV7hQ8vXFdoZR57xIz1uHUgvdVKazb69ODZo9Pw7nWt129QTEmafnjuqrMRzxsQLgCHJJmqqy7eNF0MNFR7GNH9FtMw+OG5g4jodreSYUwePpOYAh6CCwIDAQAB";

    private static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC3Em49EzLFkXutJYGLoljpQtJaj7+XaB34CntrpVW5IdYYzw9BpU2ZcqnYvXK2EACVIkvleO/9lYjz1RJPe704YHwZxhq1yfRmeN6P4m7bJpc+7yzlIR+eU4aDFsiWE7FBWThhiTzn+M4eApDYeblggvUoSYN2ku8A8BVZOI9Ty02M0CgQwVA19kq4j9wmyfyMd+xZfBe1xdPPBxVPdYmI0I4TOZoQPRSwxXuFDy9cV2hlHnvEjPW4dSC91UprNvr04Nmj0/Duda3Xb1BMSZp+eO6qsxHPGxAuAIckmaqrLt40XQw0VHsY0f0W0zD44bmDiOh2t5JhTB4+k5gCHoILAgMBAAECggEBAKm4L3T1mGsq3aJ+k+O0xYKyzTrh6zfdcXMjnJoGNDumj8p+I2GzcwZgE+/4aiE5IqEfo4cAoFTSTZ3nmcsQJzLd2A845khECwz43zQ7MpR+6VaR+2gMVji8rIZZF+pyJTE1Gpzp0wonUkbmEAxwhJhhsBACXxsNSv/uk4wccFu4G8MbtciM7sy5jrmowRZVHX8jE7ZOOwMPi7Vs333nduAQ6FTqjMYMXKxTZPSOYiq9znAyuNZaJ3ooDO8YSnjsTTwv15JclqPNLcOzBLnmZYPYHkGM8TfulbmEOeemLR64jI12ttsQnh/wQAWLnArhVNIsoZjQdTbFORhG8KxlWhECgYEA4pHoz1d32uFh1Vay92MfoSI7Xzx3dpGmv42ymo/xx04Zg6CSXPFMv+OoXDRpyKktxAunBIseju+b+4I0dGuz0p8ZILp4omwATvkDNRfZFGhZq6dNFis2OPACQyVmz3XSlMdPuo1JyCociZJD7p/DEWffp+XsSf/gEXzk54WvBpMCgYEAztoXVJfr3/edpocXZR637fSY5A60fc9DZGAJG89xIbjWu65rC8C9tzAbB/9ohGVoH5G5h3VrQkpCd0LHE0hbO+I5dExGx0nCEiQy6Qwv+a7VcecOt8/MqkZzLzZQsFpx6M1DGUsn5udZtfLlYo3Zp0E6E1pRQm0IW9zwfNtICakCgYBuCXktQgjWyAFw0GOo3H5fDrYRIdfPQRvgClyEzoYZSuf/UnvjIdopk9KPvXDm4Kt2fEZIYZycxOhXtTvukuRJ1bUpfQbbs2JO+IdDQOrQC4IJaP56VtBFxOMEY2TUOx/j3AzvyHDeAv3LHrtZaEQf7vfjnjotElVriOHPQ9DmewKBgAKgQiuQwi3VtuqmTGW8PhmHQWmjxibSushLb9IqkAUdk5xOWo2LpNzuf2lalLkZkHjWIMTXeMUerDlXX6+e+4mHFbOkREHDbfNNAlhzDyjcT1li1Yz/1llTC6NNzThv7YJHGNL5YUhNJ5ZnvwHBS0IUUehs/Rsax0BjTapIHmIBAoGBAMsqLvDaFrzX1ep3IZZKXC2hlFVh+pX0EUprd2QsF6Hs+jxlAV5kIh/jGnUO64cmRvjYUPP75vTlaiBbe8w666CqMrCZSgum/4QbtofMOrda4YggQ+aSJkUjhEJgwciO9CG29+9lCgJPNUIneH6p/TGz1RKFt+FwvjiZ0QkPVhPF";

    /**
     * 过期时间
     */
//    private static int EXPIRATION_TIME = Integer.valueOf(YamlUtil.getValue("jwt.login-timeout")) * 24 * 60 * 60 * 1000;
    private static int EXPIRATION_TIME = 3 * 24 * 60 * 60 * 1000;

    /**
     * 私钥加密token
     *
     * @param user       载荷中的数据
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String generateToken(User user, PrivateKey privateKey) {
        long time = System.currentTimeMillis() + EXPIRATION_TIME;
        Date exp = new Date(time);
        //JWT_KEY_ID为写入token中用户id
        return Jwts.builder()
                .claim(JWT_KEY_ID, user.getUserId())
                .claim(JWT_KEY_USER_NAME, user.getUserName())
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥
     * @return
     * @throws Exception
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     * @throws Exception
     */
    public static User getInfoFromToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        User user = new User();
        user.setUserId(body.get(JWT_KEY_ID, Integer.class));
        user.setUserName(body.get(JWT_KEY_USER_NAME, String.class));
        return user;
    }

    /**
     * 验证jwt
     */
    public static Response vaildateJwt(String token, PublicKey publicKey) {
        Response res;
        try {
            User user = getInfoFromToken(token, publicKey);
            res = Response.success(ResStatusEnum.SUCCESS);
        } catch (ExpiredJwtException e1) {
            //过期异常
            res = Response.error(ResStatusEnum.JWT_TIMEOUT);
        } catch (SignatureException e2) {
            //签名异常
            res = Response.error(ResStatusEnum.JWT_SIGN_EXCEPTION);
        } catch (Exception e) {
            // jwt 验证异常
            res = Response.error(ResStatusEnum.JWT_CHECK_EXCEPTION);
        }
        return res;
    }
}
