package tester;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Map;

//import org.openjdk.jmh.samples.JMHSample_01_HelloWorld
@State(Scope.Thread)
public class MapBenchmark {

    String[] resource;

    @Setup()
    public void prepare() {
        resource = new String[100000];
        for (int i = 0; i < resource.length; i++) {
            resource[i] = String.valueOf(i);
        }
    }

    private Map<String, String> reps(int reps) {
        // Map<String, String> map = new HashMap<>((int) ((float) reps / 0.75F + 1.0F)); // 使用初始化容量的构造函数
        Map<String, String> map = new HashMap<>(); // 使用默认构造函数

        for (int i = 0; i < reps; i++) {
            String test = resource[i];
            map.put(test, test);
        }
        return map;
    }

    @Benchmark
    @OperationsPerInvocation(10)
    public Map<String, String> measure_level1() {
        return reps(10);
    }

    @Benchmark
    @OperationsPerInvocation(100)
    public Map<String, String> measure_level2() {
        return reps(100);
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public Map<String, String> measure_level3() {
        return reps(1000);
    }

    @Benchmark
    @OperationsPerInvocation(10000)
    public Map<String, String> measure_level4() {
        return reps(10_000);
    }

    @Benchmark
    @OperationsPerInvocation(100000)
    public Map<String, String> measure_level5() {
        return reps(100_000);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MapBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

}