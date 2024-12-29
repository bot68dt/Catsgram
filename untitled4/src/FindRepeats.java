
    public class FindRepeats {
        int numberOfRepeats(String text, String substring) {
            /*int count=0;
            int i=0;
            int j=0;
            while(i<text.length()-1){
                j=text.indexOf(substring,i);
                if (j!=-1 && j<text.length())
                    count++;
                i=j+substring.length()-1;
            }
            return count;*/
                int count = 0;
                StringBuilder s = new StringBuilder(text);
                int i = s.indexOf(substring);
                while (i!=-1) {
                    count++;
                    s.delete(i, i+substring.length());
                    i = s.indexOf(substring);
                }
                return count;
        }
    }

