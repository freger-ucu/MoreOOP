package lotr;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class CharacterFactory {
    private static final String CHAR_PACKAGE = "lotr";
    private final List<Class<? extends Character>> characterClasses;
    private final Random random = new Random();

    public CharacterFactory() {
        this.characterClasses = discoverCharacterClasses();
    }

    public Character createCharacter() {
        if (characterClasses.isEmpty()) {
            // Fallback — manual list if discovery failed
            return fallbackCreate();
        }
        Class<? extends Character> clazz = characterClasses.get(random.nextInt(characterClasses.size()));
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return fallbackCreate();
        }
    }

    public List<Class<? extends Character>> getRegisteredCharacterClasses() {
        return Collections.unmodifiableList(characterClasses);
    }

    private Character fallbackCreate() {
        int pick = random.nextInt(4);
        switch (pick) {
            case 0: return new Hobbit();
            case 1: return new Elf();
            case 2: return new King();
            default: return new Knight();
        }
    }

    private List<Class<? extends Character>> discoverCharacterClasses() {
        List<Class<? extends Character>> result = new ArrayList<>();
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            String path = CHAR_PACKAGE.replace('.', '/');
            Enumeration<URL> resources = cl.getResources(path);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                String protocol = resource.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(resource.getFile(), "UTF-8");
                    findAndAddClassesByFile(CHAR_PACKAGE, filePath, result);
                } else if ("jar".equals(protocol)) {
                    JarURLConnection conn = (JarURLConnection) resource.openConnection();
                    try (JarFile jar = conn.getJarFile()) {
                        Enumeration<JarEntry> entries = jar.entries();
                        while (entries.hasMoreElements()) {
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            if (name.startsWith(path) && name.endsWith(".class") && !name.contains("$")) {
                                String className = name.substring(0, name.length() - 6).replace('/', '.');
                                addIfCharacterClass(className, result);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            // ignore and fallback
        }
        return result;
    }

    private void findAndAddClassesByFile(String packageName, String packagePath, List<Class<? extends Character>> result) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) return;
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                // Only consider direct subpackages if needed; avoid strategies subpackage
                if (Objects.equals(file.getName(), "kick")) continue;
                findAndAddClassesByFile(packageName + "." + file.getName(), file.getAbsolutePath(), result);
            } else if (file.getName().endsWith(".class") && !file.getName().contains("$")) {
                String simple = file.getName().substring(0, file.getName().length() - 6);
                String className = packageName + "." + simple;
                addIfCharacterClass(className, result);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void addIfCharacterClass(String className, List<Class<? extends Character>> out) {
        try {
            Class<?> clazz = Class.forName(className);
            if (Character.class.isAssignableFrom(clazz) && clazz != Character.class && !java.lang.reflect.Modifier.isAbstract(clazz.getModifiers())) {
                // has public no-arg constructor
                clazz.getDeclaredConstructor();
                out.add((Class<? extends Character>) clazz);
            }
        } catch (Throwable ignored) {
        }
    }
}

