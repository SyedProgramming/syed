class Node:
    def __init__(self, value):
        self.value=value 
        self.next = None
        self.prev = None 
        
class DoublyLinkedList:
    def __init__(self, value):
        new_node = Node(value)
        self.head = new_node
        self.tail = new_node
        self.length = 1
        
    def print_list(self):
        if self.head is None:
            print("List is empty")
        else:
            temp = self.head 
            while temp:
                print(temp.value)
                temp = temp.next 
        return True
        
    
    def append(self, value):
        new_node = Node(value)
        
        if self.head is None:
            self.head = new_node
            self.tail = new_node 
        else:
            self.tail.next = new_node
            new_node.prev = self.tail
            self.tail = new_node
        self.length += 1
        
        return True
    
    def pop(self):
        if self.length == 0:
            return None 

        temp = self.tail 
        if self.length == 1:
            self.head = None 
            self.tail = None 
        else:
            self.tail = self.tail.prev
            self.tail.next = None 
            temp.prev = None 
        return temp
    
    def prepend(self, value):
        new_node = Node(value)
        if self.length == 0:
            self.head = new_node
            self.tail = new_node
        else:
            temp = self.head
            self.head.prev = new_node
            new_node.next = self.head
            self.head = new_node
        self.length += 1
        return True
    
    def pop_first(self):
        if self.length == 0:
            return None 
        
        temp = self.head 

        if self.length == 1:
            self.head = None 
            self.tail= None 
        else:
            self.head = self.head.next
            self.head.prev = None 
            temp.next = None 
        self.length -= 1
        
    def get(self, index):
        if index < 0 or index >= self.length:
            return None 
        
        temp = self.head
        if index < self.length/2:
            for _ in range(index):
                temp = temp.next
        else:
            temp = self.tail 
            for _ in range(self.length - 1, index, -1):
                temp = temp.prev
                
        return temp
        
        
    def set_value(self, index, value):
        if self.length == 0:
            return None 
        
        temp = self.get(index)
        if temp:
            temp.value = value 
            return True 
        return False
    
    
    def insert(self, index, value):
        if index < 0 or index >= self.length:
            return False 
        
        if index == 0:
            return self.prepend(value)
        
        if index == self.length:
            return self.append(value)
            
        new_node = Node(value)
        before = self.get(index - 1)
        after = before.next 
        
        new_node.prev = before
        new_node.next = after 
        before.next = new_node
        after.prev = new_node
        
        self.length += 1
        return True
        
        
    def remove(self, index):
        if index < 0 or index >= self.length:
            return None 
        
        if index == 0:
            return self.pop_fist()
        
        if index == self.length - 1:
            return self.pop()
        
        temp = self.get(index)
        temp.next.prev = temp.prev
        temp.prev.next = temp.next 
        temp.next = None
        temp.prev = None 
        
        self.length -= 1
        
        return temp
        
        
        
        
        
    
my_double_linked_list = DoublyLinkedList(1)

my_double_linked_list.append(2)
my_double_linked_list.append(3)
my_double_linked_list.append(4)
my_double_linked_list.append(5)
my_double_linked_list.pop()
my_double_linked_list.prepend(0)
a = my_double_linked_list.pop_first()
my_double_linked_list.print_list()
print("------------------")
print(my_double_linked_list.get(2))
my_double_linked_list.set_value(0, 100)
#print("Removed Item: ", a)
print("------------------")
my_double_linked_list.print_list()
my_double_linked_list.set_value(1, 200)
print("------------------")
my_double_linked_list.print_list()
my_double_linked_list.remove(0)
print("------------------")
my_double_linked_list.print_list()