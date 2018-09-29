- You can create streams from collections, arrays, generators, or iterators.
- Use filter to select elements and map to transform elements.
- Other operations for transforming streams include limit, distinct, and sorted.
- To obtain a result from a stream, use a reduction operator such as count, max, min, findFirst, or findAny.
- You can collect stream results in collections, arrays, strings, or maps.
- The groupingBy and partitioningBy methods of the Collectors class allow you to
  split the contents of a stream into groups, and to obtain a result for each
  group.
- `long count = words.stream().filter(w -> w.length() > 12).count();`

- The stream method yields a stream for the words list. 
- The filter method returns another stream that contains only the selected words. 
 - The count method reduces that stream to a result.  
 
          Stream.of(allAppRoots)
                 .map(p -> Paths.get(p, "repos", subFolder, this.m_ComponentType, componentName))
                 .filter(Files::exists)
                 .findFirst();
     
