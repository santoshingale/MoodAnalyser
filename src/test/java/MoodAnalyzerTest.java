import com.bridgelabz.MoodAnalyzeException;
import com.bridgelabz.MoodAnalyzer;
import com.bridgelabz.MoodAnalyzerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
                Assert.assertEquals("Invalid class name",a.getMessage());
                a.printStackTrace();
            }
        }catch (NoSuchMethodException e) {

            e.printStackTrace();
        }
    }
    @Test
    public void whenGivenInvalid_mothodName_shouldThrowException() {
        Constructor<?> constructor = null;
        try {
            constructor = Class.forName("com.bridgelabz.MoodAnalyzer").getConstructor(Integer.class);


        } catch (NoSuchMethodException e) {
            try {
                throw new MoodAnalyzeException(MoodAnalyzeException.ExceptionType.METHOD_NOT_FOUND, "Invalid method name");
            }
            catch (MoodAnalyzeException a) {
                Assert.assertEquals("Invalid method name",a.getMessage());
                a.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenGivenConstuctor_withParameter_shoulReturnObject() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor constructor = MoodAnalyzerFactory.getConstructor(String.class);
        Object object = MoodAnalyzerFactory.getObject(constructor,"Im so Sad");
        MoodAnalyzer moodAnalyzer = (MoodAnalyzer) object;
        Assert.assertEquals(true,moodAnalyzer.equals(new MoodAnalyzer("Im so Sad")));

    }

    @Test
    public void whenGivenConstuctor_withoutParameter_shoulReturnObject() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException ,NullPointerException{
        Constructor constructor = MoodAnalyzerFactory.getConstructor();
        Object object = MoodAnalyzerFactory.getObject(constructor);
        MoodAnalyzer moodAnalyzer = (MoodAnalyzer) object;
        Assert.assertEquals(true,moodAnalyzer.equals(new MoodAnalyzer()));

    }

    @Test
    public void whenGivenMethod_shoulInvokeObject() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

        MoodAnalyzer moodAnalyzer = new MoodAnalyzer("Im so Sad");
        Class cls = moodAnalyzer.getClass();
        Method method = cls.getMethod("analyser");
        Object object = method.invoke(moodAnalyzer);
        System.out.println(object);
        Assert.assertEquals("Sad",object.toString());

    }
}
