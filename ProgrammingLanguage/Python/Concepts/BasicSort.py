class BasicSort:
    def bubble_sort(self, my_list):
        for i in range(len(my_list) - 1, 0, -1):
            for j in range(i):
                if my_list[j] > my_list[j+1]:
                    temp = my_list[j]
                    my_list[j] = my_list[j+1]
                    my_list[j+1] = temp 
        return my_list
    
b = BasicSort()
print(b.bubble_sort([10, 20, 60, 5, 15]))