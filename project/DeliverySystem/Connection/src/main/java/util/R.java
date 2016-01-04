package util;

/**
 * Created by Sissel on 2015/10/26.
 */
public class R {
    public static class ui{
        public static final double TitleHeight = 70;
        public static final double LeftTabsWidth = 179;
        public static final double ResizePadding = 14;

        public static final double MinStageWidth = 1146;
        public static final double MinStageHeight = 611;

        public static final double LoginWidth = 970;
        public static final double LoginHeight = 541;
    }

    public static class string{
        public final static String LocalHost = "127.0.0.1";
        public final static String CompanyAccount = "001";
        public final static String LoadDraftFail = "载入草稿失败";
        public final static String LoadDraftSuccess = "载入草稿成功";
        public final static String SaveDraftSuccess = "保存草稿成功";
    }

    public static class num{
        public final static int locsInShelf = 50;
        public final static long CheckMessageGap = 5000;
    }

    public static class path{
        public final static String InitialModelPath = "buffer/model/initialization.ser";
    }

    public static class messageKey{
        public final static String Notification = "notification233";
    }
}
