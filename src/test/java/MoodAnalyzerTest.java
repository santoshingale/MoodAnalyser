import com.bridgelabz.MoodAnalyzeException;
import com.bridgelabz.MoodAnalyzer;
import com.bridgelabz.MoodAnalyzerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static java.lang.Class.forName;

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

    @Test
    public void whenGivenObject_whenProper_shouldReturnObject() {
        Constructor<?> constructor = null;
        try {
            constructor = Class.forName("com.bridgelabz.MoodAnalyzer").getConstructor(String.class);
            Object myObject = constructor.newInstance("Im so sad right now");
            MoodAnalyzer moodAnalyzer = (MoodAnalyzer) myObject;
            String message = moodAnalyzer.analyser();
            Assert.assertEquals("Sad",message);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMoodAnalyserClass_whenProper_shouldReturnPbject() {
        MoodAnalyzer moodAnalyserFactory = MoodAnalyzerFactory.createMoodAnalyser("im so sad right now");
        MoodAnalyzer moodAnalyser = new MoodAnalyzer();
        //String analyser = moodAnalyser.analyser();
        Assert.assertEquals(true,moodAnalyserFactory.equals(moodAnalyser));
    }

    @Test
    public void givenMoodAnalyserClass_whichNotPrecent_shouldGet_notFoundExceptiom() {
        Constructor<?> constructor = null;
        try {
            constructor = Class.forName("com.bridgelabz.Improper").getConstructor(String.class);
        }catch (ClassNotFoundException e) {
            try {
                throw new MoodAnalyzeException(MoodAnalyzeException.ExceptionType.CLASS_NOT_FOUND, "Invalid class name");
            }
            catch (MoodAnalyzeException a) {
                a.printStackTrace();
            }
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


}
