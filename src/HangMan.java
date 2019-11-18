import java.util.*;

public class HangMan
{
    static Scanner input;
    static int size;
    static List<String> words;
    static String[] dispUnderSco;
    public static void main(String[] args) {
        System.out.println("First player: how many words do you wish to enter?");
        input = new Scanner(System.in);
        size = input.nextInt();
        words = getWords(size);
        dispUnderSco = displayUnderScore(words, size);

        for (int currentWord = 0; currentWord < words.size(); currentWord++) {
            int attepmts = 0;
            int mistakes = 0;
            System.out.println("The second player: Please enter a letter, if the word that the first player entered contains the letter you enter then " +
                    "we will highlight it. ");
            System.out.println("word " + (currentWord+1));
            System.out.println(dispUnderSco[currentWord].toString());
            int totalRights = words.get(currentWord).length()*2;
            String word = words.get(currentWord);
            while(totalRights > 0 && words.get(currentWord).toString().equals(dispUnderSco[currentWord]) == false)
            {
                System.out.println("You have " + totalRights + " rights left");
                char guessedLetter = input.next().charAt(0);
                if (word.contains(String.valueOf(guessedLetter))) {
                    int position = words.get(currentWord).indexOf(guessedLetter);
                    if (check(words, guessedLetter, position, currentWord)) {
                        underScoreToLetter(guessedLetter, currentWord);
                        System.out.println("You're on fire!");
                        System.out.println(dispUnderSco[currentWord].toString());
                        totalRights--;
                        attepmts++;
                        if (isFinished(currentWord))
                        {
                            System.out.println("Congratulations, you finished the word " + (currentWord+1) + " and it only took you " + attepmts + " tries. Let's move onto the word " + (currentWord+2)+".");
                        }
                    }
                }
                else {
                    System.out.println("try again");
                    System.out.println(dispUnderSco[currentWord].toString());
                    totalRights--;
                    attepmts++;
                    mistakes++;
                }
            }
        }
        }

    public static List<String> getWords(int size)
    {
        List<String> words = new ArrayList<String>(size);
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < size ; i++) {
            System.out.println("please enter the word " + (i+1));
            words.add(input.next());
        }
        return words;

    }
    public static String [] displayUnderScore (List<String> words, int size) {
        System.out.println("The player has entered " + size + " pieces of words for you to guess. Ready to guess the first one?");
        String[] elements = new String[size];
        String underScore = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < words.get(i).length(); j++) {
                underScore = underScore + "_";
                elements[i] = (underScore);
            }
            underScore ="";
        }
        return elements;
    }
        public static boolean check (List <String> words, char guessedLetter, int position, int currentWord)
//hangi kelimede olduğunu belirttiğin bir parametre iste
        {
                String word = words.get(currentWord);
                if (word.charAt(position) == guessedLetter)
                {
                    return true;
            }
        return false;
        }

    private static String [] underScoreToLetter(char guessedLetter, int currentWord)
    {
        ArrayList<Integer> moreThanOne = checkLetters(words,currentWord,guessedLetter);
        String underScore = dispUnderSco[currentWord];
        StringBuilder stringBuilder = new StringBuilder(underScore);
            Iterator iterator = moreThanOne.iterator();
            while(iterator.hasNext())
            {
                Object object = iterator.next();
                String indices = object.toString();
                int indicesInt = Integer.parseInt(indices);
                stringBuilder.setCharAt(indicesInt,guessedLetter);
                underScore = stringBuilder.toString();
                dispUnderSco[currentWord] = underScore;
            }


        return dispUnderSco;
    }
    public static ArrayList<Integer> checkLetters(List<String> words, int currentWord, char guessedLetter)
    {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        String word= words.get(currentWord);
        int size = word.length();
        int [] ints = new int[size];
        char [] chars = new char[size];
        for (int i = 0; i <size ; i++)
        {
            ints[i] = i;
            chars[i] = word.charAt(i);
            if (chars[i] == guessedLetter)
            {
                    arrayList.add(ints[i]);
            }
            //amnemaa test*

        }
            return arrayList;
    }
    public static boolean isFinished(int currentWord)
    {
        if (dispUnderSco[currentWord].equals(words.get(currentWord)))
        {
            return true;
        }
        return false;
    }
}