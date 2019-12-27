import com.bridgelabz.MoodAnalyzeException;
import com.bridgelabz.MoodAnalyzer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MoodAnalyzerTest {

    @Test
    public void whenGivenSad_shouldReturnSad() {
        MoodAnalyzer moodAnalyzer = new MoodAnalyzer("Im sad right now");
        String message = moodAnalyzer.analyser();
        Assert.assertEquals("Sad",message);

    }

    @Test
    public void whenGivenHappyMessage_shouldReturnHappy() {
        MoodAnalyzer moodAnalyzer = new MoodAnalyzer("Im happy right now");
        String message = moodAnalyzer.analyser();
        Assert.assertEquals("Happy",message);
    }

    @Test
    public void whenGivenSadMessageWithAlphabetCapital_shouldReturnSad() {
        MoodAnalyzer moodAnalyzer = new MoodAnalyzer("Im Sad right now");
        String message = moodAnalyzer.analyser();
        Assert.assertEquals("Sad",message);
    }

    @Test
    public void whenGivenNull_shouldReturnHappy() {
        try {
            MoodAnalyzer moodAnalyzer = new MoodAnalyzer(null);
            ExpectedException exception = ExpectedException.none();
            exception.expect(MoodAnalyzeException.class);
            String analyser = moodAnalyzer.analyser();
            Assert.assertEquals("Happy", analyser);
        }
        catch(Exception e){

            e.printStackTrace();
        }
    }

    @Test
    public void whenGivenNull_shouldNotGiveError() {
        try {
            MoodAnalyzer moodAnalyzer = new MoodAnalyzer(null);
            String analyser = moodAnalyzer.analyser();
            // Assert.assertEquals("Happy", analyser);
        }
        catch(MoodAnalyzeException e){

            Assert.assertEquals(MoodAnalyzeException.ExceptionType.ENTERED_NULL,e.type);
        }

    }

    @Test
    public void whenGivenEmpty_shouldNotGiveError() {
        try {
            MoodAnalyzer moodAnalyzer = new MoodAnalyzer("Im so happy today");
            String analyser = moodAnalyzer.analyser();
            // Assert.assertEquals("Happy", analyser);
        }
        catch(MoodAnalyzeException e){

            Assert.assertEquals(MoodAnalyzeException.ExceptionType.ENTERED_EMPTY,e.type);
        }

    }

}
