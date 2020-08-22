import random
import datetime

"""
Notes: 
**Use 24 Hour Clock for the code to work**
Don't use f-strings (not compatible with python version < 3.6)
"""

"""
PRINTED VALUES CORRESPONDING TO THE VARIABLES 

(Updated every second):
Time Elapsed -> time_elapsed
Time Remaining -> Time Remaining

(Updated every 15 minutes):
DC -> dc
SG -> sg
AG -> ag
Frequency -> frequency
Current Block Number -> current_block_number
Current Block Time -> current_block
Next Block Time -> next_block

Next Block's sg -> sg_plus_one
Next to Next block's sg -> sg_plus_two
and so on... -> sg_plus_three, sg_plus_four

(Updated every day)
Fuel Price -> fuel_price


"""


# Takes digit in int form and converts it to two digits (if only one) by adding preceding zero, returns str
def digit_convert(number: int) -> str:
    if len(str(number)) == 1:
        return '0' + str(number)
    else:
        return str(number)


# Takes a block number and returns a number between 1 and 96. i.e. Converts block 97 to block 1, etc.
def block_number(n):
    if n > 96:
        return n % 96
    else:
        return n


"""Read time from the system clock, using that, Assign current block number, current block and next block """
t = str(datetime.datetime.now().time())
t_hh, t_mm, t_ss = t[:2], t[3:5], t[6:8]
current_block_place = int(t_mm) // 15 + 1
next_block_place = 1 if current_block_place == 4 else current_block_place + 1

current_block_number = (int(t_hh) * 60 + int(t_mm)) // 15 + 1
current_block_start = t_hh + ":" + digit_convert((int(t_mm) // 15) * 15)
next_block_start = digit_convert(((int(t_hh) + 1) % 24) if current_block_place == 4 else t_hh) + ":" + function(t_mm)
current_block_end = next_block_start
next_block_end = digit_convert((int(next_block_start[:2]) + 1) % 24) + ":" + "00" if next_block_place == 4 else digit_convert(int(next_block_start[:2])) + ":" + digit_convert(int(next_block_start[-2:]) + 15)

current_block = current_block_start + "-" + current_block_end
next_block = next_block_start + "-" + next_block_end

"""From the time (used above) find the time elapsed and the time remaining"""
time_elapsed_mm, time_elapsed_ss = digit_convert(int(t_mm) % 15), digit_convert(int(t_ss))
time_remaining_in_seconds = digit_convert(15 * 60 - (int(time_elapsed_mm) * 60 + int(time_elapsed_ss)))
time_remaining_mm, time_remaining_ss = digit_convert(int(time_remaining_in_seconds) // 60), digit_convert(int(time_remaining_in_seconds) % 60)

time_elapsed = time_elapsed_mm + ":" + time_elapsed_ss
time_remaining = time_remaining_mm + ":" + time_remaining_ss


"""Randomizing the values as per the specified ranges"""

random.seed(current_block_number)
# Update in intervals of 15 minutes (1 time block)
dc = round(150 + random.random() * 50, 2)
sg = round(150 + random.random() * 50, 2)
ag = round(150 + random.random() * 50, 2)
frequency = round(49.5 + random.random() * 1.5, 2)

# SG for the next four blocks
random.seed(block_number(current_block_number + 1))
sg_plus_one = round(150 + random.random() * 50, 2)

random.seed(block_number(current_block_number + 2))
sg_plus_two = round(150 + random.random() * 50, 2)

random.seed(block_number(current_block_number + 3))
sg_plus_three = round(150 + random.random() * 50, 2)

random.seed(block_number(current_block_number + 4))
sg_plus_four = round(150 + random.random() * 50, 2)

# Updated every 24 hours
random.seed(int((str(datetime.date.today())[-2:])))
fuel_price = round(2 + random.random() * 8, 2)
