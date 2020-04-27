# source of partition sorting function:
# https://stackoverflow.com/questions/39192777/how-to-split-a-list-into-n-groups-in-all-possible-combinations-of-group-length-a

def sorted_k_partitions(seq, k) -> list:
    """Returns a list of all unique k-partitions of `seq`.

    Each partition is a list of parts, and each part is a tuple.

    The parts in each individual partition will be sorted in shortlex
    order (i.e., by length first, then lexicographically).

    The overall list of partitions will then be sorted by the length
    of their first part, the length of their second part, ...,
    the length of their last part, and then lexicographically.
    """
    groups = []  # Holds list of list groups
    result = generate_partitions(0, seq, k, groups)
    # Sort the parts in each partition in shortlex order
    result = [sorted(ps, key=lambda p: (len(p), p)) for ps in result]
    # Sort partitions by the length of each part, then lexicographically.
    result = sorted(result, key=lambda ps: (*map(len, ps), ps))
    return result


def generate_partitions(i, seq, k, groups) -> GeneratorExit:
    if i < len(seq):
        if len(seq) - i > k - len(groups):
            for group in groups:
                group.append(seq[i])
                yield from generate_partitions(i+1, seq, k, groups)
                group.pop()
        if len(groups) < k:
            groups.append([seq[i]])
            yield from generate_partitions(i+1, seq, k, groups)
            groups.pop()
    else:
        yield list(map(tuple, groups))

def main() -> None:
    test_cases = int(input())
    for t in range(0, test_cases):
        line = input().split()
        n = int(line[0])
        k = int(line[1])

        # list of point tuples
        coordinates = []
        # Read points
        for p in range(0, n):
            line = input().split()
            # Create a tuple of x and y coordinates
            coordinates.append((int(line[0]), int(line[1])))

        minimums_of_all_partitions = []

        for partition in sorted_k_partitions(coordinates, k):
            # Contains max diff of each group in the current partition
            group_maximums = calc_group_maximums(partition)
            # add minimum square length of current partition to list
            minimums_of_all_partitions.append(max(group_maximums))

        print("Case #" + str(t+1) + ": " + str(min(minimums_of_all_partitions)))

def calc_group_maximums(partition) -> list:
    group_maximums = []
    for group in partition:
        # calculate max x/y difference in the group
        cur_max_difference = 0
        if len(group) == 1:
            group_maximums.append(1)
            continue
        for part1 in range(len(group)):
            for part2 in range(part1 + 1, len(group)):
                cur_max_difference = max(cur_max_difference, abs(group[part2][0] - group[part1][0]),
                                         abs(group[part2][1] - group[part1][1]))
        # add max diff of x and y to group_maximums
        group_maximums.append(cur_max_difference)
    return group_maximums

if __name__ == "__main__":
    main()
