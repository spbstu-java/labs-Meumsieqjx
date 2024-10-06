package Controller;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;


class InvalidFileFormatException extends Exception{
    public InvalidFileFormatException() {
        super();
    }

    public InvalidFileFormatException(String msg) {
        super(msg);
    }

    public InvalidFileFormatException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

class FileReadException extends Exception{
    public FileReadException() {
        super();
    }

    public FileReadException(String msg) {
        super(msg);
    }

    public FileReadException(String msg, Throwable cause) {
        super(msg, cause);
    }
}


// Класс для работы со словарем
class VocabularyReader {
    private Path path;
    private Map<String, String> vocabulary = new HashMap<>();
    
    VocabularyReader(Path path) throws FileReadException {
        this.path = path;
        String fileName = path.getFileName().toString();

        // Проверяем, что файл существует 
        if (!Files.exists(path)) 
        {
            throw new FileReadException(path.toString());
        }

        // Проверяем, что файл имеет правильный формат
        if (!fileName.endsWith(".txt")) 
        {
            throw new FileReadException("File read exception: " + fileName);
        }
    }

    // Метод для чтения слов из файла и заполнения словаря vocabulary
    public void readFile() throws InvalidFileFormatException {        
        try 
        {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines)
            {
                String[] parts = line.split("\\|");
                if (parts.length == 2)
                {
                    String word = parts[0].trim().toLowerCase();  // Исходное слово
                    String translation = parts[1].trim();         // Перевод
                    this.vocabulary.put(word, translation);       // Добавляем в Map 
                }
                else
                {
                    throw new InvalidFileFormatException("Invalid file format exception");
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    // Метод для перевода заданный строки по словарю 
    public String translateText(String text) {
        String[] words = text.split(" ");
        StringBuilder translatedSentence = new StringBuilder();

        int i = 0;
        while (i < words.length)
        {
            String longest_match = null;
            String longest_translation = null;

            // Ищем максимальную комбинацию слов в словаре, начиная с i = 0 первого слова 
            for (int j = i; j < words.length; ++j)
            {
                StringBuilder stringBuilder = new StringBuilder();
                // Добавляем слова в комбинацию для проверки
                for (int k = i; k <= j; ++k)
                {
                    if (k > i)
                    {
                        stringBuilder.append(" ");
                    }
                    stringBuilder.append(words[k].toLowerCase());
                }

                String phrase = stringBuilder.toString();

                // Проверяем, есть ли такая комбинация в словаре
                if (vocabulary.containsKey(phrase))
                {
                    longest_match = phrase;
                    longest_translation = vocabulary.get(phrase);
                }
            }

            // Если совпадения есть 
            if (longest_match != null)
            {
                // Добавить максимальную комбинацию
                translatedSentence.append(longest_translation).append(" ");
                // Сдвинуть цикл на кол-во пропущенных слов 
                i = i + longest_match.split(" ").length;
            }
            else
            {
                translatedSentence.append(words[i]).append(" ");
                i++;
            }
        }


        return translatedSentence.toString().trim();
    }

    // Метод для вывода словаря 
    public void printVocabulary() {
        for (String key : this.vocabulary.keySet())
        {
            System.out.println(key + " | " + vocabulary.get(key));
        }
    }
}


public class Task3 {
    public static void main(String[] args) {
        Path path = Paths.get("Vocabulary.txt");

        try 
        {
            // Объект словаря 
            VocabularyReader vocabularyReader = new VocabularyReader(path);

            try (Scanner scanner = new Scanner(System.in)) 
            {
                System.out.print("Input the text: ");
                String text_for_translate = scanner.nextLine();
                
                try
                {
                    vocabularyReader.readFile();
                    String transaltion = vocabularyReader.translateText(text_for_translate);
                    System.out.println(transaltion);
                }
                catch (InvalidFileFormatException e)
                {
                    e.printStackTrace();
                }
            }
        } 
        catch (FileReadException e) 
        {
            e.printStackTrace(); 
        }
	}
}


// Output:
// Input the text: Dog look forward look hello look forward to something...
// собака ожидать смотреть привет ожидать to something...


// Vocabulary:
// hello | привет
// look | смотреть
// forward | вперед
// look forward | ожидать
// look out | остерегаться
// dog | собака
// look forward to something | ожидать чего-то