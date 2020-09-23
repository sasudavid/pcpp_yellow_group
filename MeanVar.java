public class MeanVar {
    public static void main(String[] args) {
        //double[] a = {25, 30.7, 30.3, 30.1,30.7,50.2,30.4,30.9,30.3,30.5,30.8};
        double[] a = {25, 30.7, 100.2, 30.1, 20.2, 30.4,2,30.3,30.5,5.4};
        int n = a.length;
        double st = 0.0;
        double sst = 0.0;

        for (int j = 0; j<n; j++){
            st+=a[j];
            sst += a[j] * a[j]; 
        }

        double mean = st/n;
        double sdev = Math.sqrt((sst - mean*mean*n)/(n));
        System.out.printf("%6.1f ns +/- %6.3f%n", mean, sdev);
    }
}
