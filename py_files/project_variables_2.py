import random
import datetime

"""Note: Use 24 Hour Clock for the code to work"""

"""
PRINTED VALUES CORRESPONDING TO THE VARIABLES 

DC -> dc
SG -> sg
AG -> ag
Frequency -> frequency
Fuel Price -> fuel_price
Current Block Number -> current_block_number
Current Block Time -> current_block
Next Block Time -> next_block
Time Elapsed -> time_elapsed
Time Remaining -> Time Remaining
"""


# Takes digit in int form and converts it to two digits (if only one) by adding preceding zero, returns str
def digit_convert(number: int) -> str:
    if len(str(number)) == 1:
        return '0' + str(number)
    else:
        return str(number)


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

"""Code to find time elapsed and time remaining"""

time_elapsed_mm, time_elapsed_ss = digit_convert(int(t_mm) % 15), digit_convert(int(t_ss))
time_remaining_in_seconds = digit_convert(15 * 60 - (int(time_elapsed_mm) * 60 + int(time_elapsed_ss)))
time_remaining_mm, time_remaining_ss = digit_convert(int(time_remaining_in_seconds) // 60), digit_convert(int(time_remaining_in_seconds) % 60)

time_elapsed = f'{time_elapsed_mm}:{time_elapsed_ss}'
time_remaining = f'{time_remaining_mm}:{time_remaining_ss}'



""""
Code for updating the variables follow:

Update after time interval: All updated when time advances to the next time block
Fuel Price is updated only when the day changes i.e. at Time Block 1
"""

""" 
Detects the start of the next block then starts updating values every 15 minutes
else waits for 1 minute and checks again
"""

"""Initialization"""
random.seed(int(current_block_start[:2]) + int(current_block_start[3:5]))
# Update in intervals of 15 minutes (1 time block)
dc = round(150 + random.random() * 50, 2)
sg = round(150 + random.random() * 50, 2)
ag = round(150 + random.random() * 50, 2)

frequency = round(49.5 + random.random() * 1.5, 2)

# Updated every 24 hours
fuel_price = round(2 + random.random() * 8, 2)
