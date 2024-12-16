package src.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import src.Frame.AccountMenuFrame;
import src.Frame.AccountProfileFrame;
import src.Frame.AddFriendFrame;
import src.Frame.EditAccountFrame;
import src.Frame.EditProfileFrame;
import src.Frame.LoginFrame;
import src.Frame.ProfileDisplayFrame;
import src.Frame.ProfileMenuFrame;
import src.Frame.RegisterFrame;
import src.Frame.UserFrame;
import src.Profile;
import src.Server.Client;
import src.Server.Server;
import src.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.Socket;
import java.util.ArrayList;

@RunWith(Enclosed.class)
public class RunLocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
        System.exit(0);
    }

    /**
     * A set of public test cases.
     *
     * <p>Purdue University -- CS18000 -- Fall 2020</p>
     *
     * @author Purdue CS
     * @version August 24, 2020
     */

    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }
        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        //////////////////////////////////////////////////////////////////////////////////////////////
        // Test Cases for all classes
        ///////////////////////////////////////////////////////////////////////////////////////////////

        // Frame.AccountMenuFrame
        @Test(timeout = 1_000)
        public void accountMenuFrameClassDeclarationTest() {
            Class<?> clazz;
            String className;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            // Set the class being tested
            clazz = AccountMenuFrame.class;
            className = "Frame.AccountMenuFrame";

            // Perform tests

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `" + className +
                    "` is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className +
                    "` is NOT `abstract`!", Modifier.isAbstract(modifiers));

            Assert.assertEquals("Ensure that `" + className +
                    "` extends `JOptionPane`!", JOptionPane.class, superclass);

            Assert.assertEquals("Ensure that `" + className +
                    "` implements 'Runnable'", 1, superinterfaces.length);

        }
        @Test(timeout = 1_000)
        public void accountMenuFrameParameterizedConstructorDeclarationTest() {
            Class<?> clazz;
            String className = "accountMenuFrame";
            Constructor<?> constructor;
            int modifiers;
            Class<?>[] exceptions;
            int expectedLength = 0;

            clazz = AccountMenuFrame.class;

            try {
                constructor = clazz.getDeclaredConstructor(Socket.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and " +
                        "has one parameters with types Socket!");

                return;
            } //end try catch

            modifiers = constructor.getModifiers();

            exceptions = constructor.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s parameterized constructor is `public`!",
                    Modifier.isPublic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s parameterized constructor " +
                    "has an empty `throws` clause!", expectedLength, exceptions.length);
        }
        ///////////////////////////////////////////////////////////////////////
        // exempt
        @Test(timeout = 1000)
        public void accountMenuFrameRunMethodTest() {
            Class<?> clazz;
            String className = "accountMenuFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "run";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;


            // Set the class being tested
            clazz = AccountMenuFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` " +
                        "that" + " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName
                    + "` method is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName
                    + "` method is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName
                    + "` method has the correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName
                    + "` method has an empty `throws` clause!", expectedLength, exceptions.length);

        }
        /////////////////////////////////////////////////////////////////////////////
        // Frame.AccountProfileFrame
        @Test(timeout = 1_000)
        public void accountProfileFrameClassDeclarationTest() {
            Class<?> clazz;
            String className;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            // Set the class being tested
            clazz = AccountProfileFrame.class;
            className = "Frame.AccountProfileFrame";

            // Perform tests

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `"+ className +"` is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `"+ className +"` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));

            Assert.assertEquals("Ensure that `"+ className +"` extends `JOptionPane`!",
                    JOptionPane.class, superclass);

            Assert.assertEquals("Ensure that `"+ className +"` implements 'Runnable'",
                    1, superinterfaces.length);

        }
        @Test(timeout = 1_000)
        public void accountProfileFrameParameterizedConstructorDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.AccountProfileFrame";
            Constructor<?> constructor;
            int modifiers;
            Class<?>[] exceptions;
            int expectedLength = 0;

            clazz = AccountProfileFrame.class;

            try {
                constructor = clazz.getDeclaredConstructor(Socket.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has one parameters with types Socket!");

                return;
            } //end try catch

            modifiers = constructor.getModifiers();

            exceptions = constructor.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s parameterized constructor is `public`!",
                    Modifier.isPublic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s parameterized constructor has an empty " +
                    "`throws` clause!", expectedLength, exceptions.length);
        }
        ///////////////////////////////////////////////////////////////////////
        // exempt
        @Test(timeout = 1000)
        public void accountProfileFrameRunMethodTest() {
            Class<?> clazz;
            String className = "Frame.AccountProfileFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "run";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;


            // Set the class being tested
            clazz = AccountProfileFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }
        ////////////////////////////////////////////////////////////////////////
        @Test(timeout = 1_000)
        public void addFriendFrameFieldDeclarationTest1() {
            Class<?> clazz;
            String className = "Frame.AddFriendFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "columnName";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = String[].class;

            // Set the class being tested
            clazz = AddFriendFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!",
                    Modifier.isPrivate(modifiers));
            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!",
                    Modifier.isFinal(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }
        @Test(timeout = 1_000)
        public void addFriendFrameParameterizedConstructorDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.AddFriendFrame";
            Constructor<?> constructor;
            int modifiers;
            Class<?>[] exceptions;
            int expectedLength = 0;

            clazz = AddFriendFrame.class;

            try {
                constructor = clazz.getDeclaredConstructor(Socket.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has one parameters with types Socket!");

                return;
            } //end try catch

            modifiers = constructor.getModifiers();

            exceptions = constructor.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s parameterized constructor is `public`!",
                    Modifier.isPublic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s parameterized constructor has an empty " +
                    "`throws` clause!", expectedLength, exceptions.length);
        }
        ///////////////////////////////////////////////////////////////////////
        // Exempt
        @Test(timeout = 1000)
        public void addFriendFrameRunMethodTest() {
            Class<?> clazz;
            String className = "Frame.AddFriendFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "run";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;


            // Set the class being tested
            clazz = AddFriendFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }
        @Test(timeout = 1000)
        public void addFriendFrameUpdateAllUserModelMethodTest() {
            Class<?> clazz;
            String className = "Frame.AddFriendFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "updateAllUserModel";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = DefaultTableModel.class;


            // Set the class being tested
            clazz = AddFriendFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);


            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` works correctly " +
                    "with proper input!", expectedLength, exceptions.length);
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` fails with " +
                    "improper input!", expectedLength, exceptions.length);

        }
        @Test(timeout = 1000)
        public void addFriendFrameUpdatePendingModelMethodTest() {
            Class<?> clazz;
            String className = "Frame.AddFriendFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "updatePendingModel";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = DefaultTableModel.class;


            // Set the class being tested
            clazz = AddFriendFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }
        @Test(timeout = 1000)
        public void addFriendFrameUpdateRequestModelMethodTest() {
            Class<?> clazz;
            String className = "Frame.AddFriendFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "updateRequestModel";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = DefaultTableModel.class;


            // Set the class being tested
            clazz = AddFriendFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }
        @Test(timeout = 1000)
        public void addFriendFrameUpdateAllMethodTest() {
            Class<?> clazz;
            String className = "Frame.AddFriendFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "updateAll";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;


            // Set the class being tested
            clazz = AddFriendFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }
        ///////////////////////////////////////////////////////////////
        // Frame.EditAccountFrame
        @Test(timeout = 1_000)
        public void editAccountFrameClassDeclarationTest() {
            Class<?> clazz;
            String className;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            // Set the class being tested
            clazz = EditAccountFrame.class;
            className = "Frame.EditAccountFrame";

            // Perform tests

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `"+ className +"` is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `"+ className +"` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));

            Assert.assertEquals("Ensure that `"+ className +"` extends `JOptionPane`!", JOptionPane.class,
                    superclass);

            Assert.assertEquals("Ensure that `"+ className +"` implements 'Runnable'", 1,
                    superinterfaces.length);

        }
        @Test(timeout = 1_000)
        public void editAccountFrameParameterizedConstructorDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.EditAccountFrame";
            Constructor<?> constructor;
            int modifiers;
            Class<?>[] exceptions;
            int expectedLength = 0;

            clazz = EditAccountFrame.class;

            try {
                constructor = clazz.getDeclaredConstructor(Socket.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has one parameters with types Socket!");

                return;
            } //end try catch

            modifiers = constructor.getModifiers();

            exceptions = constructor.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s parameterized constructor is `public`!",
                    Modifier.isPublic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s parameterized constructor has an empty " +
                    "`throws` clause!", expectedLength, exceptions.length);
        }
        ///////////////////////////////////////////////////////////////////////
        @Test(timeout = 1000)
        public void editAccountFrameRunMethodTest() {
            Class<?> clazz;
            String className = "Frame.EditAccountFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "run";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;


            // Set the class being tested
            clazz = EditAccountFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }
        @Test(timeout = 1000)
        public void editAccountFrameContentCheckMethodTest() {
            Class<?> clazz;
            String className = "Frame.EditAccountFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "contentCheck";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = boolean.class;


            // Set the class being tested
            clazz = EditAccountFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, String.class, String.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }
        /////////////////////////////////////////////////////////////////////////////
        // Frame.EditProfileFrame
        @Test(timeout = 1_000)
        public void editProfileFrameClassDeclarationTest() {
            Class<?> clazz;
            String className;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            // Set the class being tested
            clazz = EditProfileFrame.class;
            className = "Frame.EditProfileFrame";

            // Perform tests

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `"+ className +"` is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `"+ className +"` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));

            Assert.assertEquals("Ensure that `"+ className +"` extends `JComponent`!", JComponent.class,
                    superclass);

            Assert.assertEquals("Ensure that `"+ className +"` implements 'Runnable'", 1,
                    superinterfaces.length);

        }
        @Test(timeout = 1_000)
        public void editProfileFrameParameterizedConstructorDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.EditProfileFrame";
            Constructor<?> constructor;
            int modifiers;
            Class<?>[] exceptions;
            int expectedLength = 0;

            clazz = EditProfileFrame.class;

            try {
                constructor = clazz.getDeclaredConstructor(Socket.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has one parameters with types Socket!");

                return;
            } //end try catch

            modifiers = constructor.getModifiers();

            exceptions = constructor.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s parameterized constructor is `public`!",
                    Modifier.isPublic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s parameterized constructor has an empty " +
                    "`throws` clause!", expectedLength, exceptions.length);
        }
        ///////////////////////////////////////////////////////////////////////
        @Test(timeout = 1000)
        public void editProfileFrameRunMethodTest() {
            Class<?> clazz;
            String className = "Frame.EditProfileFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "run";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;


            // Set the class being tested
            clazz = EditProfileFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }
        @Test(timeout = 1000)
        public void editProfileFrameContentCheckMethodTest() {
            Class<?> clazz;
            String className = "Frame.EditProfileFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "contentCheck";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = boolean.class;


            // Set the class being tested
            clazz = EditProfileFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, String.class, String.class, String.class, String.class, String.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }
        /////////////////////////////////////////////////////////////////////////////
        // Profile
        @Test(timeout = 1_000)
        public void profileClassDeclarationTest() {
            Class<?> clazz;
            String className;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            // Set the class being tested
            clazz = Profile.class;
            className = "Profile";

            // Perform tests

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `"+ className +"` is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `"+ className +"` is NOT `abstract`!",
                     Modifier.isAbstract(modifiers));

            //Assert.assertEquals("Ensure that `"+ className +"` extends `JComponent`!", JComponent.class, superclass);

            //Assert.assertEquals("Ensure that `"+ className +"` implements 'Runnable'", 1, superinterfaces.length);

        }
        @Test(timeout = 1_000)
        public void profileParameterizedConstructorDeclarationTest() {
            Class<?> clazz;
            String className = "Profile";
            Constructor<?> constructor;
            int modifiers;
            Class<?>[] exceptions;
            int expectedLength = 0;

            clazz = Profile.class;

            try {
                constructor = clazz.getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has one parameters with types Socket!");

                return;
            } //end try catch

            modifiers = constructor.getModifiers();

            exceptions = constructor.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s parameterized constructor is `public`!",
                    Modifier.isPublic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s parameterized constructor has an empty " +
                    "`throws` clause!", expectedLength, exceptions.length);
        }
        @Test(timeout = 1000)
        public void profileMethodTest1() {
            Class<?> clazz;
            String className = "Profile";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "getPhoneNumber";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = String.class;


            // Set the class being tested
            clazz = Profile.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }
        ///////////////////////////////////////////////////////////////////////////
        // Frame.ProfileDisplayFrame
        @Test(timeout = 1_000)
        public void profileDisplayFrameClassDeclarationTest() {
            Class<?> clazz;
            String className;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            // Set the class being tested
            clazz = ProfileDisplayFrame.class;
            className = "Frame.ProfileDisplayFrame";

            // Perform tests

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `"+ className +"` is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `"+ className +"` is NOT `abstract`!",
                     Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `"+ className +"` extends `JComponent`!", JComponent.class,
                    superclass);
            Assert.assertEquals("Ensure that `"+ className +"` implements 'Runnable'", 1,
                    superinterfaces.length);

        }
        @Test(timeout = 1_000)
        public void profileDisplayFrameParameterizedConstructorDeclarationTest() {
            Class<?> clazz;
            String className = "LogProfileDisplayFrameinFrame";
            Constructor<?> constructor;
            int modifiers;
            Class<?>[] exceptions;
            int expectedLength = 0;

            clazz = ProfileDisplayFrame.class;

            try {
                constructor = clazz.getDeclaredConstructor(Socket.class, String.class, String.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has one parameters with types Socket!");

                return;
            } //end try catch

            modifiers = constructor.getModifiers();

            exceptions = constructor.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s parameterized constructor is `public`!",
                    Modifier.isPublic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s parameterized constructor has an empty " +
                    "`throws` clause!", expectedLength, exceptions.length);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////
        @Test(timeout = 1000)
        public void profileDisplayFrameRunMethodTest() {
            Class<?> clazz;
            String className = "LoginProfileDisplayFrameFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "run";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;


            // Set the class being tested
            clazz = ProfileDisplayFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);
            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }
        //////////////////////////////////////////////////////////
        // Login Frame
        @Test(timeout = 1_000)
        public void loginFrameClassDeclarationTest() {
            Class<?> clazz;
            String className;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            // Set the class being tested
            clazz = LoginFrame.class;
            className = "Frame.LoginFrame";

            // Perform tests

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `" + className + "` is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));

            Assert.assertEquals("Ensure that `" + className + "` extends `JComponent`!", JComponent.class,
                    superclass);

            Assert.assertEquals("Ensure that `" + className + "` implements 'Runnable'", 1,
                    superinterfaces.length);

        }

        @Test(timeout = 1_000)
        public void loginFrameFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.LoginFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "loginFrame";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = JFrame.class;

            // Set the class being tested
            clazz = LoginFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                     "correct type!", expectedType, type);
        }

        @Test(timeout = 1_000)
        public void userIdLabelFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.LoginFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "userIdLabel";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = JLabel.class;

            // Set the class being tested
            clazz = LoginFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                     "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                     "correct type!", expectedType, type);
        }

        @Test(timeout = 1_000)
        public void userIdFieldFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.LoginFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "userIdField";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = JTextField.class;

            // Set the class being tested
            clazz = LoginFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }
        @Test(timeout = 1_000)
        public void passwordLabelFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.LoginFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "passwordLabel";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = JLabel.class;

            // Set the class being tested
            clazz = LoginFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }
        @Test(timeout = 1_000)
        public void passwordFieldFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.LoginFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "passwordField";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = JPasswordField.class;

            // Set the class being tested
            clazz = LoginFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }
        @Test(timeout = 1_000)
        public void loginButtonFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.LoginFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "loginButton";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = JButton.class;

            // Set the class being tested
            clazz = LoginFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }
        @Test(timeout = 1_000)
        public void registerButtonFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.LoginFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "registerButton";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = JButton.class;

            // Set the class being tested
            clazz = LoginFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }
        @Test(timeout = 1_000)
        public void socketFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.LoginFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "socket";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = Socket.class;

            // Set the class being tested
            clazz = LoginFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                     "correct type!", expectedType, type);
        }
        @Test(timeout = 1_000)
        public void bufferedReaderFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.LoginFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "bufferedReader";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = BufferedReader.class;

            // Set the class being tested
            clazz = LoginFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName +
                    "` field is NOT `static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName +
                    "` field is the correct type!", expectedType, type);
        }
        @Test(timeout = 1_000)
        public void printWriterFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.LoginFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "printWriter";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = PrintWriter.class;

            // Set the class being tested
            clazz = LoginFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }
        @Test(timeout = 1_000)
        public void userIdFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.LoginFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "userId";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = String.class;

            // Set the class being tested
            clazz = LoginFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }
        @Test(timeout = 1_000)
        public void actionListenerFieldDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.LoginFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "actionListener";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = ActionListener.class;

            // Set the class being tested
            clazz = LoginFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }

        @Test(timeout = 1_000)
        public void LoginFrameParameterizedConstructorDeclarationTest() {
            Class<?> clazz;
            String className = "Frame.LoginFrame";
            Constructor<?> constructor;
            int modifiers;
            Class<?>[] exceptions;
            int expectedLength = 0;

            clazz = LoginFrame.class;

            try {
                constructor = clazz.getDeclaredConstructor(Socket.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has one parameters with types Socket!");

                return;
            } //end try catch

            modifiers = constructor.getModifiers();

            exceptions = constructor.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s parameterized constructor is `public`!",
                    Modifier.isPublic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s parameterized constructor has an empty " +
                    "`throws` clause!", expectedLength, exceptions.length);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////
        @Test(timeout = 1000)
        public void runMethodTest() {
            Class<?> clazz;
            String className = "Frame.LoginFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "run";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;


            // Set the class being tested
            clazz = LoginFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }

        @Test(timeout = 1_000)
        public void RegisterFrameFieldDeclarationTestOne() {

            Class<?> clazz = RegisterFrame.class;
            String className = "Frame.RegisterFrame";

            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "registerFrame";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = JFrame.class;


            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is
            // `private`!", Modifier.isPrivate(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }

        @Test(timeout = 1_000)
        public void actionListenerFieldDeclarationTestRegisterFrame() {
            Class<?> clazz;
            String className = "Frame.RegisterFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "actionListener";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = ActionListener.class;

            // Set the class being tested
            clazz = RegisterFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }
        @Test(timeout = 1_000)
        public void RegisterFrameParameterizedConstructorDeclarationTest() {

            Class<?> clazz = RegisterFrame.class;
            String className = "Frame.RegisterFrame";

            Constructor<?> constructor;
            int modifiers;
            Class<?>[] exceptions;
            int expectedLength = 2;

            try {
                constructor = clazz.getDeclaredConstructor(Socket.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has three parameters with type String, String, and double!");

                return;
            } //end try catch

            modifiers = constructor.getModifiers();

            exceptions = constructor.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s parameterized constructor is `public`!",
                    Modifier.isPublic(modifiers));

            //Assert.assertEquals("Ensure that `" + className + "`'s parameterized constructor has an two
            // `throws` clauses!", expectedLength, exceptions.length);
        }
        ///////////////////////////////////////////////////////////////////////////////////////
        @Test(timeout = 1000)
        public void runMethodTestRegisterFrame() {
            Class<?> clazz;
            String className = "Frame.RegisterFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "run";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;


            // Set the class being tested
            clazz = RegisterFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }
        @Test(timeout = 1000)
        public void RegisterFrameMethodTestOne() {

            Class<?> clazz = RegisterFrame.class;
            String className = "Frame.RegisterFrame";

            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "contentCheck";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = boolean.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, String.class, String.class, String.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }

        @Test(timeout = 1_000)
        public void socketFieldDeclarationTestUserFrame() {
            Class<?> clazz;
            String className = "Frame.UserFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "socket";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = Socket.class;

            // Set the class being tested
            clazz = UserFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                     "correct type!", expectedType, type);
        }
        @Test(timeout = 1_000)
        public void bufferedReaderFieldDeclarationTestUserFrame() {
            Class<?> clazz;
            String className = "Frame.UserFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "bufferedReader";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = BufferedReader.class;

            // Set the class being tested
            clazz = UserFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }
        @Test(timeout = 1_000)
        public void printWriterFieldDeclarationTestUserFrame() {
            Class<?> clazz;
            String className = "Frame.UserFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "printWriter";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = PrintWriter.class;

            // Set the class being tested
            clazz = UserFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }
        @Test(timeout = 1_000)
        public void userFrameFieldDeclarationTest1() {
            Class<?> clazz;
            String className = "Frame.UserFrame";
            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "columnName";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = String[].class;

            // Set the class being tested
            clazz = UserFrame.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!", Modifier.isPrivate(modifiers));

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `final`!", Modifier.isFinal(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }

        @Test(timeout = 1_000)
        public void UserFrameParameterizedConstructorDeclarationTest() {

            Class<?> clazz = UserFrame.class;
            String className = "Frame.UserFrame";

            Constructor<?> constructor;
            int modifiers;
            Class<?>[] exceptions;
            int expectedLength = 2;

            try {
                constructor = clazz.getDeclaredConstructor(Socket.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has two parameters with types String and String!");

                return;
            } //end try catch

            modifiers = constructor.getModifiers();

            exceptions = constructor.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s parameterized constructor is `public`!",
                    Modifier.isPublic(modifiers));

            //Assert.assertEquals("Ensure that `" + className + "`'s parameterized constructor has an two
            // `throws` clauses!", expectedLength, exceptions.length);
        }
        /////////////////////////////////////////////////////////////////////////////////////////
        @Test(timeout = 1000)
        public void runMethodTestUserFrame() {
            Class<?> clazz;
            String className = "Frame.UserFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "run";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = void.class;


            // Set the class being tested
            clazz = UserFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }
        @Test(timeout = 1000)
        public void updateModelMethodTestUserFrame() {
            Class<?> clazz;
            String className = "Frame.UserFrame";
            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "updateModel";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = DefaultTableModel.class;


            // Set the class being tested
            clazz = UserFrame.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                    "empty `throws` clause!", expectedLength, exceptions.length);

        }
        /////////////////////////////////////////////////////////////////////////////////////////
        // ProfileClient
        @Test(timeout = 1_000)
        public void ProfileClientClassDeclarationTest() {

            Class<?> clazz = Client.class;
            String className = "ProfileClient";

            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            // Perform tests

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `"+ className +"` is `public`!", Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `"+ className +"` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));

            Assert.assertEquals("Ensure that `"+ className +"` extends `Object`!", Object.class, superclass);

            //Assert.assertEquals("Ensure that `"+ className +"` implements 1 interface!", 1,
            // superinterfaces.length);
        }

        @Test(timeout = 1_000)
        public void UserFieldDeclarationTestOne() {

            Class<?> clazz = User.class;
            String className = "User";

            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "userId";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = String.class;


            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is `private`!",
                    Modifier.isPrivate(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }

        @Test(timeout = 1_000)
        public void UserParameterizedConstructorDeclarationTest() {

            Class<?> clazz = User.class;
            String className = "User";

            Constructor<?> constructor;
            int modifiers;
            Class<?>[] exceptions;
            int expectedLength = 2;

            try {
                constructor = clazz.getDeclaredConstructor(String.class, String.class, String.class, String.class);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a constructor that is `public` and has two parameters of type String and String!");

                return;
            } //end try catch

            modifiers = constructor.getModifiers();

            exceptions = constructor.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s parameterized constructor is `public`!",
                    Modifier.isPublic(modifiers));

            //Assert.assertEquals("Ensure that `" + className + "`'s parameterized constructor has an two
            // `throws` clauses!", expectedLength, exceptions.length);
        }

        ///////////////////////////////////////////////////////////////////
        @Test(timeout = 1000)
        public void methodTestOne() {

            Class<?> clazz = User.class;
            String className = "User";

            Method method;
            int modifiers;
            Class<?> actualReturnType;
            int expectedLength = 0;
            Class<?>[] exceptions;

            // Set the method that you want to test
            String methodName = "getRequestList";

            // Set the return type of the method you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedReturnType = ArrayList.class;

            // Attempt to access the class method
            try {
                method = clazz.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                        " has no parameters!");

                return;
            } //end try catch

            // Perform tests

            modifiers = method.getModifiers();

            actualReturnType = method.getReturnType();

            exceptions = method.getExceptionTypes();

            Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName + "` method is `public`!"
                    , Modifier.isPublic(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName + "` method is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has the " +
                    "correct return type!", expectedReturnType, actualReturnType);

            Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName + "` method has an " +
                     "empty `throws` clause!", expectedLength, exceptions.length);

        }

        @Test(timeout = 1_000)
        public void profileServerFieldDeclarationTestOne() {

            Class<?> clazz = Server.class;
            String className = "ProfileServer";

            Field testField;
            int modifiers;
            Class<?> type;

            // Set the field that you want to test
            String fieldName = "socket";

            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = Socket.class;

            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch

            // Perform tests

            modifiers = testField.getModifiers();

            type = testField.getType();

            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is
            // `private`!", Modifier.isPrivate(modifiers));

            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));

            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }

        /////////////////////////////////////////////////////////////////////////////////////////
        // Frame.ProfileMenuFrame
        @Test(timeout = 1_000)
        public void menuFrameFrameClassDeclarationTest() {
            Class<?> clazz = ProfileMenuFrame.class;
            String className = "Frame.ProfileMenuFrame";
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;
            // Perform tests
            modifiers = clazz.getModifiers();
            superclass = clazz.getSuperclass();
            superinterfaces = clazz.getInterfaces();
            Assert.assertTrue("Ensure that `"+ className +"` is `public`!", Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `"+ className +"` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `"+ className +"` extends `JComponent`!", JComponent.class,
                    superclass);
            Assert.assertEquals("Ensure that `"+ className +"` implements Runnable!", 1,
                    superinterfaces.length);
        }

        @Test(timeout = 1_000)
        public void menuFrameFieldDeclarationTestOne() {
            Class<?> clazz = ProfileMenuFrame.class;
            String className = "Frame.ProfileMenuFrame";
            Field testField;
            int modifiers;
            Class<?> type;
            // Set the field that you want to tes
            String fieldName = "socket";
            // Set the type of the field you want to test
            // Use the type + .class
            // For example, String.class or int.class
            Class<?> expectedType = Socket.class;
            // Attempt to access the class field
            try {
                testField = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                Assert.fail("Ensure that `" + className + "` declares a field named `" + fieldName + "`!");

                return;
            } //end try catch
            // Perform tests
            modifiers = testField.getModifiers();
            type = testField.getType();
            //Assert.assertTrue("Ensure that `" + className + "`'s `" + fieldName + "` field is
            // `private`!", Modifier.isPrivate(modifiers));
            Assert.assertFalse("Ensure that `" + className + "`'s `" + fieldName + "` field is NOT " +
                    "`static`!", Modifier.isStatic(modifiers));
            Assert.assertEquals("Ensure that `" + className + "`'s `" + fieldName + "` field is the " +
                    "correct type!", expectedType, type);
        }
    }
}