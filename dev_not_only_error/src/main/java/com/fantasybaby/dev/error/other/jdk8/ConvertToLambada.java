package com.fantasybaby.dev.error.other.jdk8;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ConvertToLambada {
    /**
     * @param ints
     * @return
     */
    private static double calc(List<Integer> ints) {
        //临时中间集合
        List<Point2D> point2DList = new ArrayList<>();
        for (Integer i : ints) {
            point2DList.add(new Point2D.Double((double) i % 3, (double) i / 3));
        }
        //临时变量，纯粹是为了获得最后结果需要的中间变量
        double total = 0;
        int count = 0;

        for (Point2D point2D : point2DList) {
            //过滤
            if (point2D.getY() > 1) {
                //算距离
                double distance = point2D.distance(0, 0);
                total += distance;
                count++;
            }
        }
        //注意count可能为0的可能
        return count > 0 ? total / count : 0;
    }

    public static void convertToStream(List<Integer> ints) {
        double v = ints.stream().map(i -> new Point2D.Double((double) i % 3, (double) i / 3)).filter(point -> point.getY() > 1)
                .mapToDouble(point -> point.distance(0, 0)).average().orElse(0);
        System.out.println(v);
    }


    /**
     * 递归遍历所有文件夹
     *
     * @throws IOException
     */
    public void filesExample() throws IOException {
        //无限深度，递归遍历文件夹
        try (Stream<Path> pathStream = Files.walk(Paths.get("."))) {
            pathStream.filter(Files::isRegularFile) //只查普通文件
                    .filter(FileSystems.getDefault().getPathMatcher("glob:**/*.java")::matches) //搜索java源码文件
                    .flatMap(ThrowingFunction.unchecked(path ->
                            Files.readAllLines(path).stream() //读取文件内容，转换为Stream<List>
                                    .filter(line -> Pattern.compile("public class").matcher(line).find()) //使用正则过滤带有public class的行
                                    .map(line -> path.getFileName() + " >> " + line))) //把这行文件内容转换为文件名+行
                    .forEach(System.out::println); //打印所有的行
        }
    }

    public static void main(String[] args) {
        List ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println(ConvertToLambada.calc(ints));
        ConvertToLambada.convertToStream(ints);
    }
}
