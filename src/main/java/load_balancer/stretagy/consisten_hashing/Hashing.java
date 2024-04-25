package load_balancer.stretagy.consisten_hashing;

public class Hashing {

    private static final int MODULO = new Double(10e9).intValue();

    private static final int PRIME = 31;

    public static int hash(String ip){
        return hash(ip, 0);
    }

    public static int hash(String ip, int replicationNumber){
        String[] ipComponents = ip.split("\\.");
        int hash = 0;
        for(String ipComponent : ipComponents){
            hash = appendIntoHash(hash, Integer.valueOf(ipComponent));
        }
        hash = appendIntoHash(hash, replicationNumber);
        return hash;
    }

    private static int appendIntoHash(int hash, Integer value){
        return (PRIME*hash + value)%MODULO;
    }
}