class Node:
    def __init__(self, value):
        self.value = value
        self.next = None
        

class LinkedList:
    def __init__(self, value):
        new_node = Node(value)
        self.head = new_node
        self.tail = new_node
        self.length=1
    
    def print_list(self):
        temp = self.head
        while temp is not None:
            print(temp.value)
            temp=temp.next
    
    def make_empty(self):
        self.head = None
        self.tail = None
        self.length = 0
        
    def append(self, value):
        new_node = Node(value)
        
        if self.head is None:
            self.head = new_node
            self.tail = new_node
        else:
            self.tail.next = new_node
            self.tail = new_node
        self.length += 1
               
    def pop(self):
        if self.length == 0:
            return None 
        
        temp = self.head
        pre = self.head 
        
        while(temp.next):
            pre = temp
            temp = temp.next
        
        self.tail=pre 
        self.tail.next=None 
        self.length -= 1
        
        if self.length == 0:
            self.head=None 
            self.tail=None 
        return temp
    
    
    def pop_first(self):
        if self.length == 0:
            return None 
        
        temp = self.head
        self.head = self.head.next
        
        temp.next=None
        self.length -= 1
        
        if self.length == 0:
            self.head = None 
            self.tail = None
        return temp
    
    def prepend(self, value):
        new_node = Node(value)
        if self.length == 0:
            self.head = new_node
            self.tail = new_node
        
        else:
            new_node.next = self.head
            self.head = new_node
        
        self.length += 1
        
        return True
            
    
    def get(self, index):
        if index < 0 or index >= self.length:
            return None 
        
        temp = self.head
        
        for _ in range(index):
            print(_)
            temp = temp.next
        return temp
    
    def set(self, index, value):
        if index < 0 or index >= self.length:
            return None 
        
        temp = self.get(index)
        
        if temp: # if temp <> None
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
        temp = self.get(index-1)
        new_node.next = temp.next
        temp.next = new_node
        self.length += 1
        
        return True
    
    
    def remove(self, index):
        if index < 0 or index >= self.length:
            return None 
        
        if index == 0:
            return self.pop_first()
        
        if index == self.length:
            return self.pop()
        
        pre = self.get(index-1)
        temp = pre.next
        pre.next = temp.next 
        temp.next = None 
        self.length -= 1
        
        return temp

            
    def reverse(self):
        prev = None 
        current = self.head
        
        while current:
            next_node = current.next
            current.next = prev
            
            prev = current
            current = next_node
        
        self.head = prev
        


my_linked_list = LinkedList(1)

my_linked_list.append(2)
my_linked_list.append(3)
my_linked_list.append(4)
my_linked_list.append(5)
my_linked_list.append(6)



#my_linked_list.print_list()
#my_linked_list.pop()
#print("---------------------")
my_linked_list.print_list()


#removed_item = my_linked_list.pop_first()

#print("---------------------")
#my_linked_list.print_list()
#print("Removed Item value: ", removed_item.value)


#print("Head: ", my_linked_list.head.value)
#print("Tail: ", my_linked_list.tail.value)
#print("Length: ", my_linked_list.length, '\n')
#print("---------------------")

#my_linked_list.prepend(-1)
#my_linked_list.print_list()
#print("---------------------")
#print(my_linked_list.get(1).value)
#my_linked_list.set(1, 100)
#print("---------------------")
#my_linked_list.print_list()
#my_linked_list.insert(2,200)
#print("---------------------")
#my_linked_list.print_list()
#my_linked_list.remove(0)
#print("---------------------")
#my_linked_list.print_list()
print("---------------------")
my_linked_list.reverse()
my_linked_list.print_list()